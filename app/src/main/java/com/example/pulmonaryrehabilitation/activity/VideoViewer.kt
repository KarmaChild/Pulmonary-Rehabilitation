package com.example.pulmonaryrehabilitation.activity

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.R

class VideoViewer : AppCompatActivity() {
    // declaring null variable for video viewer
    var videoViewer: VideoView? = null
    // declaring null variable for media controller
    var mediaControls: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view)
        videoViewer = findViewById<View>(R.id.videoView) as VideoView

        if (mediaControls == null) {
            // creating an object of media controller class
            mediaControls = MediaController(this)

            // set the anchor view for the video view
            mediaControls!!.setAnchorView(this.videoViewer)
        }

        // set the media controller for video view
        videoViewer!!.setMediaController(mediaControls)

        // setting absolute path of video to be played
        videoViewer!!.setVideoURI(
            Uri.parse(
                "android.resource://" +
                    packageName + "/" + R.raw.countdown
            )
        )

        videoViewer!!.requestFocus()

        // starting the video
        videoViewer!!.start()

        // display a toast message
        // after the video is completed
        videoViewer!!.setOnCompletionListener {
            Toast.makeText(
                applicationContext, "Video completed", Toast.LENGTH_LONG
            ).show()
        }

        // display a toast message if any
        // error occurs while playing the video
        videoViewer!!.setOnErrorListener { mp, what, extra ->
            Toast.makeText(
                applicationContext,
                "An Error Occurred " +
                    "While Playing Video !!!",
                Toast.LENGTH_LONG
            ).show()
            false
        }
    }
}