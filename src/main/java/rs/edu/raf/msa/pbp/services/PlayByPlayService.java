package rs.edu.raf.msa.pbp.services;

import org.springframework.stereotype.Service;
import rs.edu.raf.msa.pbp.model.Play;
import rs.edu.raf.msa.pbp.model.PlayByPlay;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayByPlayService {


    public List<Play> play(PlayByPlay game, String fromMin, String toMin) {
        int minutes = Integer.parseInt(fromMin.substring(0,2)); //0

        int quarterStart = minutes / 12;
        int minuteInQuarter = 12 + (12*quarterStart-minutes);

        String minutes1 = Integer.toString(minuteInQuarter);
        if(minuteInQuarter < 10){
            minutes1 = "0" + minutes1;
        }
        LocalTime timeStart1 = LocalTime.parse(minutes1 + ":00");
        LocalTime timeStart = timeStart1.minus((Integer.parseInt(fromMin.substring(3))), ChronoUnit.MINUTES);

        minutes = Integer.parseInt(toMin.substring(0,2));
        int quarterEnd = minutes/ 12;
        int minuteInQuarterEnd = 12 + (12*quarterEnd-minutes);

        minutes1 = Integer.toString(minuteInQuarterEnd);
        if(minuteInQuarterEnd<10){
            minutes1="0" + minutes1;
        }
        LocalTime timeEnd1 = LocalTime.parse(minutes1 + ":00");

        LocalTime timeEnd = timeEnd1.minus((Integer.parseInt(toMin.substring(3))), ChronoUnit.MINUTES);

        int flag = 0;

        List<Play> plejevi = new ArrayList<>();
        for(int i = quarterStart; i<= quarterEnd; i++){
            if(i ==4)
                break;
            for(Play play: game.getQuarters().get(i).getPlays()){
                String sati = play.getClock();

                if(sati.length()>4){
                    sati = sati.substring(0,5);
                }

                LocalTime candidate = LocalTime.parse(sati);

                if(flag == 0 && i==quarterStart && (candidate.isBefore(timeStart) || candidate.equals(timeStart))) {//07:00 8:00 6
                    flag = 1;

                }

                if((i == quarterEnd) && (candidate.isBefore(timeEnd))) {
                    flag = 0;
                    break;
                }
                if(play.getDescription().equals("Start Period") || play.getDescription().equals("End Period"))
                    continue;

                if(flag == 1)
                    plejevi.add(play);


            }
        }
        System.out.println("Broj plejeva: " + plejevi.size());
        System.out.println(plejevi);
        return plejevi;

    }
}
