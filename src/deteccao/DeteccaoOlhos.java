/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deteccao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author silva
 */
public class DeteccaoOlhos {
        public static void main(String args[]){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        Mat imagemColorida;
        imagemColorida = Imgcodecs.imread("src\\pessoas\\faceolho.jpg");
        
        Mat imagemCinza = new Mat();
        Imgproc.cvtColor(imagemColorida,imagemCinza, COLOR_BGR2GRAY);
        
        CascadeClassifier classificadorOlho = new CascadeClassifier("src\\cascades\\haarcascade_eye.xml");
        //CascadeClassifier classificador = new CascadeClassifier("src\\cascades\\banana_classifier.xml");
        
        MatOfRect olhosDetectados = new MatOfRect();
        classificadorOlho.detectMultiScale(imagemCinza, olhosDetectados);
        
        System.out.println(olhosDetectados.toArray().length);
        
         Rect[] facesArray = olhosDetectados.toArray();
         for(int  i  =  0 ;  i  <  facesArray . length ; i++){
            Imgproc.rectangle(imagemColorida, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 0, 255), 2);
                        
        } 
        Utilitarios ut = new Utilitarios();
        ut.mostraImagem(ut.convertMatToImage(imagemColorida));
    }
}
