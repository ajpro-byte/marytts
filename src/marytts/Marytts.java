/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marytts;

import java.util.Arrays;
import java.util.List;
 import javax.sound.sampled.AudioInputStream;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.signalproc.effects.JetPilotEffect;
import marytts.signalproc.effects.LpcWhisperiserEffect;
import marytts.signalproc.effects.RobotiserEffect;
import marytts.signalproc.effects.StadiumEffect;
import marytts.signalproc.effects.VocalTractLinearScalerEffect;
import marytts.signalproc.effects.VolumeEffect;
import marytts.util.data.audio.AudioPlayer;
 
public class Marytts
{
     
    private MaryInterface marytts;
    private AudioPlayer ap;
            
    
    
//    public void Marytts(String voiceName)
//    {
//        
//        
//        try
//        {
//            marytts = new LocalMaryInterface();
//            marytts.setVoice(voiceName);
//            ap = new AudioPlayer();
//        }
//        catch (MaryConfigurationException ex)
//        {
//            ex.printStackTrace();
//        }
//    }
//    
//    public void say(String input)
//    {
//        
//        try
//        {
//            AudioInputStream audio = marytts.generateAudio(input);
//            
//            ap.setAudio(audio);
//            ap.start();
//        }
//        catch (SynthesisException ex)
//        {
//            System.err.println("Error saying phrase.");
//        }
//    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
		TextToSpeech tts = new TextToSpeech();
		tts.getAudioEffects().stream().forEach(audioEffect -> {
			System.out.println("-----Name-----");
			System.out.println(audioEffect.getName());
			System.out.println("-----Examples-----");
			System.out.println(audioEffect.getExampleParameters());
			System.out.println("-----Help Text------");
			System.out.println(audioEffect.getHelpText() + "\n\n");
			
		});
		tts.getAvailableVoices().stream().forEach(voice -> System.out.println("Voice: " + voice));
		
		// Setting the Current Voice
		tts.setVoice("cmu-rms-hsmm");
                
                VocalTractLinearScalerEffect vocalTractLSE = new VocalTractLinearScalerEffect(); //russian drunk effect
		vocalTractLSE.setParams("amount:70");
		JetPilotEffect jetPilotEffect = new JetPilotEffect(); //epic fun!!!
		jetPilotEffect.setParams("amount:100");
		RobotiserEffect robotiserEffect = new RobotiserEffect();
		robotiserEffect.setParams("amount:50");
		StadiumEffect stadiumEffect = new StadiumEffect();
		stadiumEffect.setParams("amount:150");
		LpcWhisperiserEffect lpcWhisperiserEffect = new LpcWhisperiserEffect(); //creepy
		lpcWhisperiserEffect.setParams("amount:70");
		VolumeEffect volumeEffect = new VolumeEffect(); //be careful with this i almost got heart attack
		volumeEffect.setParams("amount:0");
		tts.getMarytts().setAudioEffects(stadiumEffect.getFullEffectAsString());
                
		tts.speak("Customer number 1 please proceed to payment counter number 6 ", 2.0f, false, true);
		
    
    }
}


