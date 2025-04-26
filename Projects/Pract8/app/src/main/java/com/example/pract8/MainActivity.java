package com.example.pract8;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.JsonReader;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Handler mainHandler = new Handler(Looper.getMainLooper());
        Button buttonSequential = findViewById(R.id.button_main_sequential);
        buttonSequential.setOnClickListener(v ->
        {
            setCurrentTime(R.id.text_main_seq1Start);
            startSequentialThreads(mainHandler);
        });
        Button buttonParallel = findViewById(R.id.button_main_parallel);
        buttonParallel.setOnClickListener(v ->
        {
            startParallelThreads(mainHandler);
        });
        Button buttonRest = findViewById(R.id.button_main_rest);
        buttonRest.setOnClickListener(v ->
        {
            RestThread imageThread = new RestThread(mainHandler);
            imageThread.start();
        });
    }

    private void setCurrentTime(int textViewId)
    {
        TextView text = findViewById(textViewId);
        String time = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime.now());
        text.setText(time);
    }

    private void startSequentialThreads(Handler mainHandler)
    {
        MyThread sequentialThread1 = new MyThread(mainHandler, 1000);
        MyThread sequentialThread2 = new MyThread(mainHandler, 2000);
        MyThread sequentialThread3 = new MyThread(mainHandler, 3000);
        sequentialThread1.setOnFinished(() ->
        {
            setCurrentTime(R.id.text_main_seq1End);
            setCurrentTime(R.id.text_main_seq2Start);
            sequentialThread2.start();
        });
        sequentialThread2.setOnFinished(() ->
        {
            setCurrentTime(R.id.text_main_seq2End);
            setCurrentTime(R.id.text_main_seq3Start);
            sequentialThread3.start();
        });
        sequentialThread3.setOnFinished(() ->
                setCurrentTime(R.id.text_main_seq3End));
        sequentialThread1.start();
    }

    private void startParallelThreads(Handler mainHandler)
    {
        MyThread parallelThread1 = new MyThread(mainHandler, 2000);
        MyThread parallelThread2 = new MyThread(mainHandler, 4000);
        parallelThread1.setOnFinished(() -> setCurrentTime(R.id.text_main_par1End));
        parallelThread2.setOnFinished(() -> setCurrentTime(R.id.text_main_par2End));
        setCurrentTime(R.id.text_main_par1Start);
        setCurrentTime(R.id.text_main_par2Start);
        parallelThread1.start();
        parallelThread2.start();
    }

    public class MyThread extends Thread
    {
        private final Handler mainHandler;
        private Runnable onFinished;
        private final long duration;

        public MyThread(Handler mainHandler, long duration)
        {
            this.mainHandler = mainHandler;
            this.duration = duration;
        }

        public void setOnFinished(Runnable onFinished)
        {
            this.onFinished = onFinished;
        }

        @Override
        public void run()
        {
            try
            {
                Thread.sleep(duration);
                mainHandler.post(onFinished);
            }
            catch (InterruptedException ignored) { }
        }
    }
    
    public class RestThread extends Thread
    {
        private final Handler mainHandler;

        public RestThread(Handler mainHandler)
        {
            this.mainHandler = mainHandler;
        }

        @Override
        public void run()
        {
            Bitmap image = getImage();
            if (image == null)
            {
                mainHandler.post(() ->
                        Toast.makeText(MainActivity.this,
                                "При загрузке картинки произошла ошибка",
                                Toast.LENGTH_SHORT).show());
                return;
            }
            mainHandler.post(() ->
            {
                ImageView imageView = findViewById(R.id.image_main_restApi);
                imageView.setImageBitmap(image);
            });
        }

        private Bitmap getImage()
        {
            try
            {
                URL imageUrl = new URL(getStringByUrl("https://random.dog/woof.json"));
                HttpsURLConnection imageConnection = (HttpsURLConnection) imageUrl.openConnection();
                imageConnection.setDoInput(true);
                imageConnection.connect();
                InputStream input = imageConnection.getInputStream();
                return BitmapFactory.decodeStream(input);
            }
            catch (IOException e)
            {
                return null;
            }
        }

        private String getStringByUrl(String url) throws IOException
        {
            URL resourceUrl = new URL(url);
            HttpsURLConnection resourceConnection = (HttpsURLConnection) resourceUrl.openConnection();
            if (resourceConnection.getResponseCode() == 200)
            {
                InputStream response = resourceConnection.getInputStream();
                InputStreamReader responseReader = new InputStreamReader(response, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseReader);
                jsonReader.beginObject();
                while (jsonReader.hasNext())
                {
                    String name = jsonReader.nextName();
                    if (!name.equals("url"))
                    {
                        jsonReader.skipValue();
                        continue;
                    }
                    return jsonReader.nextString();
                }
                return null;
            }
            return null;
        }
    }
}