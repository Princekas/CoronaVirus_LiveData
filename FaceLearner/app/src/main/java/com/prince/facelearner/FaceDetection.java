package com.prince.facelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.camerakit.CameraKitView;
import com.prince.facelearner.Helper.GraphicOverlay;

import dmax.dialog.SpotsDialog;

public class FaceDetection extends AppCompatActivity {
Button facedetect;
GraphicOverlay graphicOverlay;
CameraKitView cameraKitView;
AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detection);
        facedetect=findViewById(R.id.detectface);
        graphicOverlay=findViewById(R.id.graphic_overly);
        cameraKitView=findViewById(R.id.camera_view);
        alertDialog=new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Please wait ,Loading...")
                .setCancelable(false).build();


facedetect.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        cameraKitView.startVideo();
        cameraKitView.captureImage(null);
        graphicOverlay.clear();
    }
});

    }
    @Override
    protected void onPause() {

        super.onPause();
        cameraKitView.stopVideo();
    }
    @Override
    protected void onResume() {

        super.onResume();
        cameraKitView.startVideo();
    }
}
