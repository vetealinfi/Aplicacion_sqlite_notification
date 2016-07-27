package util;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by CTN Developer on 26-07-2016.
 */
public class AndroidUtils {



    public static boolean isHayConexion(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void enviarNotificacion(Context context, String mensaje , int drawableIcon ,Class<?> activity){
        int notificationId = 001;
        // construir el intento para la notificacion
        Intent viewIntent = new Intent(context, activity);
        viewIntent.putExtra("sss", "PARAMETRO");
        PendingIntent viewPendingIntent = PendingIntent.getActivity(context, 0, viewIntent, 0);


        System.out.println("entro antes de notification");
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setContentTitle("Notificación")
                        .setContentText(mensaje)
                        .setContentIntent(viewPendingIntent);

        /*
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(drawableIcon)
                        .setContentTitle("Notificación")
                        .setContentText(mensaje)
                        .setContentIntent(viewPendingIntent);
        */
        System.out.println("va a vibrar");
        //Vibracion
        notificationBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        System.out.println("enciende led");
        //LED
        notificationBuilder.setLights(Color.RED, 3000, 3000);

        // obtener una instancia del servicio NotificationManager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        // construir la notificacion con el notificationManager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }


}
