package nicksapps;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Rational;

public class ScreenRecorder extends javax.swing.JFrame {

    public ScreenRecorder() {
        initComponents();
        initFieldValues();
        resumeTimer();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tlxField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tlyField = new javax.swing.JTextField();
        whToggle = new javax.swing.JToggleButton();
        brLabel = new javax.swing.JLabel();
        brxLabel = new javax.swing.JLabel();
        brxField = new javax.swing.JTextField();
        bryLabel = new javax.swing.JLabel();
        bryField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cursorXLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cursorYLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        durationField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        fpsField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        maxFPSLabel = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        delayField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fnField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Bounding Box");

        jLabel2.setText("Top Left Corner:");

        jLabel3.setText("x:");

        tlxField.setText("0");
        tlxField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tlxFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("y:");

        tlyField.setText("0");
        tlyField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tlyFieldActionPerformed(evt);
            }
        });

        whToggle.setText("Click for Width/Height");
        whToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                whToggleActionPerformed(evt);
            }
        });

        brLabel.setText("Bottom Right Corner:");

        brxLabel.setText("x:");

        brxField.setText("0");
        brxField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brxFieldActionPerformed(evt);
            }
        });

        bryLabel.setText("y:");

        bryField.setText("0");
        bryField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bryFieldActionPerformed(evt);
            }
        });

        jLabel8.setText("Cursor Currently At:");

        jLabel9.setText("x:");

        cursorXLabel.setText("0");

        jLabel11.setText("y:");

        cursorYLabel.setText("0");

        jLabel13.setText("Time");

        jLabel14.setText("Duration:");

        jLabel16.setText("s");

        durationField.setText("0");
        durationField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                durationFieldActionPerformed(evt);
            }
        });

        jLabel15.setText("FPS:");

        fpsField.setText("0");
        fpsField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fpsFieldActionPerformed(evt);
            }
        });

        jLabel17.setText("fps");

        jLabel18.setText("Maximum FPS:");

        maxFPSLabel.setText("0");

        jLabel20.setText("fps");

        jLabel21.setText("Delay:");

        delayField.setText("0");
        delayField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delayFieldActionPerformed(evt);
            }
        });

        jLabel22.setText("s");

        startButton.setText("START");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        messageLabel.setText(" ");

        jLabel5.setText("Output filename:");

        fnField.setText("output");

        jLabel6.setText(".mp4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(whToggle)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tlxField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tlyField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(2, 2, 2)
                                .addComponent(cursorXLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cursorYLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(brxLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(brxField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bryLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bryField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addComponent(startButton)
                        .addGap(64, 64, 64))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(brLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(durationField)
                            .addComponent(fpsField)
                            .addComponent(maxFPSLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(delayField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22))
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fnField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(122, 122, 122)
                                .addComponent(jLabel13)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(durationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tlxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(tlyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(fpsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(whToggle)
                    .addComponent(jLabel18)
                    .addComponent(maxFPSLabel)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(delayField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(brLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(brxLabel)
                            .addComponent(brxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bryLabel)
                            .addComponent(bryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cursorXLabel)
                            .addComponent(jLabel11)
                            .addComponent(cursorYLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(startButton)))
                .addGap(13, 13, 13)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageLabel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tlyFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tlyFieldActionPerformed
        try {
            tly = Integer.parseInt(tlyField.getText());
            if (tly<0) throw new NumberFormatException();
            messageLabel.setText(" ");
        } catch(NumberFormatException e) {
            messageLabel.setForeground(Color.red);
            messageLabel.setText(NONINTEGER_WARNING);
        }
    }//GEN-LAST:event_tlyFieldActionPerformed

    private void whToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_whToggleActionPerformed
        wh = !wh;
        if (wh) {
            whToggle.setText("Click for X/Y");
            brLabel.setText("Bounding Box Dims:");
            brxLabel.setText("w:");
            bryLabel.setText("h:");
        } else {
            whToggle.setText("Click for Width/Height");
            brLabel.setText("Bottom Right Corner:");
            brxLabel.setText("x:");
            bryLabel.setText("y:");
        }
    }//GEN-LAST:event_whToggleActionPerformed

    private void brxFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brxFieldActionPerformed
        try {
            if (wh) {
                width = Integer.parseInt(brxField.getText());
            } else {
                width = Integer.parseInt(brxField.getText())-tlx;
            }
            if (width<0) throw new NumberFormatException();
            messageLabel.setText(" ");
        } catch (NumberFormatException e) {
            messageLabel.setForeground(Color.red);
            messageLabel.setText(NONINTEGER_WARNING);
        }
    }//GEN-LAST:event_brxFieldActionPerformed

    private void fpsFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fpsFieldActionPerformed
        try {
            fps = Integer.parseInt(fpsField.getText());
            if (fps<0) throw new NumberFormatException();
            messageLabel.setText(" ");
        } catch(NumberFormatException e) {
            messageLabel.setForeground(Color.red);
            messageLabel.setText(NONINTEGER_WARNING);
        }
    }//GEN-LAST:event_fpsFieldActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // Prepare variables
        pauseTimer();
        width+= ((width%2==0)?0:1);
        height+= ((height%2==0)?0:1);
        Rectangle box = new Rectangle(tlx,tly,width,height);
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(ScreenRecorder.class.getName()).log(Level.SEVERE, null, ex);
            messageLabel.setForeground(Color.red);
            messageLabel.setText("Fatal: Failed to initialize robot.");
            forceUpdate();
        }
        ArrayList<BufferedImage> imgArr = new ArrayList<BufferedImage>(duration/fps);
        
        // Record screenshots
        for (int s=delay;s>0;s--) {
            messageLabel.setText("Will begin recording in " + s);
            forceUpdate();
            r.delay(1000);
        }
        messageLabel.setText("Recording...");
        forceUpdate();
        long startTime = System.currentTimeMillis();
        long lastTime = startTime;
        while (System.currentTimeMillis()<startTime+duration*1000) {
            if (System.currentTimeMillis()>=lastTime+1000/fps) {
                lastTime = System.currentTimeMillis();
                imgArr.add(r.createScreenCapture(box));
            }
        }
        int actualFPS = imgArr.size()/duration;
        messageLabel.setText("Processing recording. FPS=" + actualFPS);
        forceUpdate();
        
        // Save video
        SeekableByteChannel out = null;
        try {
            out = NIOUtils.writableFileChannel(fnField.getText() + ".mp4");
            AWTSequenceEncoder encoder = new AWTSequenceEncoder(out, Rational.R(actualFPS, 1));
            for (BufferedImage img : imgArr) {
                encoder.encodeImage(img);
            }
            encoder.finish();
        } catch (IOException e) {
            messageLabel.setForeground(Color.red);
            messageLabel.setText("Fatal: Error writing to file.");
            forceUpdate();
        } finally {
            NIOUtils.closeQuietly(out);
        }
        
        messageLabel.setText("Completed file save. FPS=" + actualFPS);
        resumeTimer();
    }//GEN-LAST:event_startButtonActionPerformed

    private void tlxFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tlxFieldActionPerformed
        try {
            tlx = Integer.parseInt(tlxField.getText());
            if (tlx<0) throw new NumberFormatException();
            messageLabel.setText(" ");
        } catch(NumberFormatException e) {
            messageLabel.setForeground(Color.red);
            messageLabel.setText(NONINTEGER_WARNING);
        }
    }//GEN-LAST:event_tlxFieldActionPerformed

    private void bryFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bryFieldActionPerformed
        try {
            if (wh) {
                height = Integer.parseInt(bryField.getText());
            } else {
                height = Integer.parseInt(bryField.getText())-tly;
            }
            if (height<0) throw new NumberFormatException();
            messageLabel.setText(" ");
        } catch (NumberFormatException e) {
            messageLabel.setForeground(Color.red);
            messageLabel.setText(NONINTEGER_WARNING);
        }
    }//GEN-LAST:event_bryFieldActionPerformed

    private void durationFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_durationFieldActionPerformed
        try {
            duration = Integer.parseInt(durationField.getText());
            if (duration<0) throw new NumberFormatException();
            messageLabel.setText(" ");
        } catch(NumberFormatException e) {
            messageLabel.setForeground(Color.red);
            messageLabel.setText(NONINTEGER_WARNING);
        }
    }//GEN-LAST:event_durationFieldActionPerformed

    private void delayFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delayFieldActionPerformed
        try {
            delay = Integer.parseInt(delayField.getText());
            if (delay<0) throw new NumberFormatException();
            messageLabel.setText(" ");
        } catch(NumberFormatException e) {
            messageLabel.setForeground(Color.red);
            messageLabel.setText(NONINTEGER_WARNING);
        }
    }//GEN-LAST:event_delayFieldActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScreenRecorder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScreenRecorder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScreenRecorder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScreenRecorder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScreenRecorder().setVisible(true);
            }
        });
    }
    
    private void initFieldValues() {
        // Bounding Box
        tlx = 0;
        tlxField.setText(tlx+"");
        tly = 0;
        tlyField.setText(tly+"");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)screenSize.getWidth();
        brxField.setText(width+"");
        height = (int)screenSize.getHeight();
        bryField.setText(height+"");
        
        // Max FPS
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(ScreenRecorder.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        ArrayList<BufferedImage> imgArr = new ArrayList<BufferedImage>();
        long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis()<startTime+1000) {
            imgArr.add(r.createScreenCapture(new Rectangle(width,height)));
        }
        int maxFPS = (int)Math.ceil(1000.0/imgArr.size());
        maxFPSLabel.setText(maxFPS+"");
        
        // Time
        duration = 30;
        durationField.setText(duration+"");
        fps = Math.min(7, maxFPS);
        fpsField.setText(fps+"");
        delay = 4;
        delayField.setText(delay+"");
    }
    
    private void forceUpdate() {
        messageLabel.paintImmediately(messageLabel.getVisibleRect());
        this.revalidate();
        this.repaint();
    }
    
    private class updatePointer extends TimerTask {
        @Override
        public void run() {
            Point current = MouseInfo.getPointerInfo().getLocation();
            cursorXLabel.setText(current.x+"");
            cursorYLabel.setText(current.y+"");
        }
    }
    
    private void pauseTimer() {
        timer.cancel();
    }
    
    private void resumeTimer() {
        timer = new Timer();
        timer.schedule(new updatePointer(), 0, 100);
    }
    
    private int tlx;
    private int tly;
    private int width;
    private int height;
    private boolean wh;
    private int duration;
    private int fps;
    private int delay;
    private Timer timer;
    
    public static final String NONINTEGER_WARNING = "All parameters must be nonnegative integers. Check corners.";

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel brLabel;
    private javax.swing.JTextField brxField;
    private javax.swing.JLabel brxLabel;
    private javax.swing.JTextField bryField;
    private javax.swing.JLabel bryLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel cursorXLabel;
    private javax.swing.JLabel cursorYLabel;
    private javax.swing.JTextField delayField;
    private javax.swing.JTextField durationField;
    private javax.swing.JTextField fnField;
    private javax.swing.JTextField fpsField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel maxFPSLabel;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton startButton;
    private javax.swing.JTextField tlxField;
    private javax.swing.JTextField tlyField;
    private javax.swing.JToggleButton whToggle;
    // End of variables declaration//GEN-END:variables
}