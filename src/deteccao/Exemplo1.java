/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deteccao;

import java.awt.Point;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author silva
 */
public class Exemplo1 {
    public static void main(String args[]){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        Mat imagemColorida;
        imagemColorida = Imgcodecs.imread("src\\pessoas\\beatles.jpg");
        
        Mat imagemCinza = new Mat();
        Imgproc.cvtColor(imagemColorida,imagemCinza, COLOR_BGR2GRAY);
        
        CascadeClassifier classificador = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_default.xml");
        //CascadeClassifier classificador = new CascadeClassifier("src\\cascades\\banana_classifier.xml");
        MatOfRect facesDetectadas = new MatOfRect();
        classificador.detectMultiScale(imagemCinza, facesDetectadas, 
                1.19,
                3,
                0,
                new Size(30,30),
                new Size(500,500));
        
        System.out.println(facesDetectadas.toArray().length);
        
        Rect[] facesArray = facesDetectadas.toArray();
        
        
        for(int  i  =  0 ;  i  <  facesArray . length ;  i ++){
            Imgproc.rectangle(imagemColorida, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 0, 255), 2);
            
        }
        
        
        CascadeClassifier classificadorOlho = new CascadeClassifier("src\\cascades\\haarcascade_eye.xml");
        MatOfRect olhosDetectados = new MatOfRect();
        classificadorOlho.detectMultiScale(imagemCinza, olhosDetectados);
        
        System.out.println(olhosDetectados.toArray().length);
        
        Rect[] olhosArray = olhosDetectados.toArray();
              
        for(int  i  =  0 ;  i  <  olhosArray . length ;  i ++){
            Imgproc.rectangle(imagemColorida, olhosArray[i].tl(), olhosArray[i].br(), new Scalar(0, 255, 0), 2);
            
            
        }
        Utilitarios ut = new Utilitarios();
        ut.mostraImagem(ut.convertMatToImage(imagemColorida));
        
    }
}
