/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deteccao;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
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
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author silva
 */
public class webcam extends javax.swing.JFrame {

    /**
     * Creates new form webcam
     */
    public webcam() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
               webcam janela = new webcam();
               janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               janela.setSize(600,500);
               janela.setVisible(true);
               janela.mostraVideo();
    }
    public void mostraVideo(){
        Mat video = new Mat();
        VideoCapture captura = new VideoCapture(0); 
            if(captura.isOpened()){
                while (true){
                    captura.read(video);
                    if(!video.empty()){
                        setSize(video.width()+ 50, video.height() + 70);
                        Mat imagemColorida = video;
                        
                        Mat imagemCinza = new Mat();
                        Imgproc.cvtColor(imagemColorida,imagemCinza, COLOR_BGR2GRAY);
        
                        CascadeClassifier classificador = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_default.xml");
                        
                        MatOfRect facesDetectadas = new MatOfRect();
                        classificador.detectMultiScale(imagemCinza, facesDetectadas,
                            1.1,1,0, new Size(150,150), new Size(500, 500));
        
                        System.out.println(facesDetectadas.toArray().length);
        
                        Rect[] facesArray = facesDetectadas.toArray();
        
        
                        for(int  i  =  0 ;  i  <  facesArray . length ;  i ++){
                            Imgproc.rectangle(imagemColorida, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 0, 255), 2);
            
                        }
                        
                        BufferedImage imagem = new Utilitarios().convertMatToImage(video);
                        Graphics g = jPanel1.getGraphics();
                        g.drawImage(imagem, 10, 10, imagem.getWidth(),imagem.getHeight(),null);
                        
                    }
                }
            }
            
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
