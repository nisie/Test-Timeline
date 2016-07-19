package com.nisieapp.nis.testtimeline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    EditText title;
    EditText content;
    Button addImage;
    Button upload;
    ImageView previewImage;

    Cloudinary cloudinary;
    String imagepath;
    String imageUrl;

    ProgressDialog dialog;
    Shop shop;

    private static final String APP_KEY = "9F1108C7-03B0-E405-FFC4-FADA845C2800";
    private static final String SECRET_KEY = "BF53B182-3C9B-47C8-FF39-AF4B94D4B500";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String appVersion = "v1";
        Backendless.initApp(this, APP_KEY, SECRET_KEY, appVersion);

        Map config = new HashMap();
        config.put("cloud_name", "nisie");
        config.put("api_key", "369735983594184");
        config.put("api_secret", "by6jQu5ngaud0VLLnJ6TOLH46qE");
        cloudinary = new Cloudinary(config);

        previewImage = (ImageView) findViewById(R.id.previewImage);
        title = (EditText) findViewById(R.id.titleText);
        content = (EditText) findViewById(R.id.contentText);
        addImage = (Button) findViewById(R.id.addButton);
        upload = (Button) findViewById(R.id.createButton);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
            }
        });


        StringBuilder whereClause = new StringBuilder();
        whereClause.append("shopId = 1");

        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause.toString());

        Backendless.Persistence.of(Shop.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Shop>>() {
            @Override
            public void handleResponse(BackendlessCollection<Shop> listShop) {
                Log.i("NISNIS GET SHOP", listShop.getCurrentPage().get(0).shopName);
                shop = listShop.getCurrentPage().get(0);
                upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (imagepath != null) {
                            dialog = ProgressDialog.show(PostActivity.this, "", "Uploading file...", true);
                            new Thread(new Runnable() {
                                public void run() {
                                    uploadFile(imagepath);
                                }
                            }).start();
                        }
                    }

                });
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.e("NISNISERR", backendlessFault.toString());
            }
        });


    }

    private void uploadFile(String imagepath) {
        try {
            Map result = cloudinary.uploader().upload(new File(imagepath), ObjectUtils.emptyMap());
            imageUrl = result.get("url").toString();

            Timeline timeline = new Timeline();
            timeline.setContext(content.getText().toString().trim());
            timeline.setShopId("1");
            timeline.setType(111);
            timeline.setImgUrl(imageUrl);
            if (shop != null)
                timeline.setShop(shop);


            Backendless.Persistence.save(timeline, new AsyncCallback<Timeline>() {
                @Override
                public void handleResponse(Timeline timeline) {
                    Log.i("NISNIS", timeline.getShopId() + "has been saved");
                    dialog.dismiss();
                    finish();
                }

                @Override
                public void handleFault(BackendlessFault backendlessFault) {
                    Log.i("NISNIS ERR", backendlessFault.toString());

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Bitmap photo = (Bitmap) data.getData().getPath();
            //Uri imagename=data.getData();
            Uri selectedImageUri = data.getData();
            imagepath = getPath(selectedImageUri);
            Bitmap bitmap = BitmapFactory.decodeFile(imagepath);
            previewImage.setImageBitmap(bitmap);

        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
