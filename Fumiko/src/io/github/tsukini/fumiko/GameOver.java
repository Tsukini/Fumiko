package io.github.tsukini.fumiko;

import org.flixel.FlxG;
import org.flixel.FlxSave;
import org.flixel.FlxState;
import org.flixel.FlxText;
import com.badlogic.gdx.utils.Array;

public class GameOver extends FlxState {

	private FlxText gameOver, message, score, bScore;
	private float time = 0;
	private int score2 = 0, lastScore, bestScore;
		
	@Override
	public void create() {

        FlxSave gameSave = new FlxSave();
        gameSave.bind("fumikoSave");


        if (FlxG.scores.size > 0){
            lastScore = FlxG.scores.get(0);
            FlxG.scores.set(0, FlxG.score);
            bestScore = FlxG.scores.get(1);
        } else {
            lastScore = 0;
            FlxG.scores.add(FlxG.score);
            bestScore = 0;
            FlxG.scores.add(FlxG.score);
        }

        if (FlxG.score > bestScore){
            bestScore = FlxG.score;
        }

        gameSave.data.put("score", FlxG.scores);
        gameSave.flush();

	    FlxG.saves = new Array<FlxSave>();

        if (FlxG.saves.size < 0){
			FlxG.saves.add(gameSave);
		}

		
		gameOver = new FlxText(FlxG.width/4, FlxG.height/4, FlxG.width/2, "GAME OVER!");
		gameOver.setSize(50);
		gameOver.setAlignment("center");
				
		message = new FlxText(FlxG.width/4, FlxG.height/4, FlxG.width/2);
		message.setSize(20);
		message.setAlignment("center");
		
		/*
		lScore = new FlxText(0, FlxG.height/2, FlxG.width/4);
		lScore.setSize(20);
		lScore.setAlignment("center");
		*/

		bScore = new FlxText(FlxG.width - FlxG.width/4, FlxG.height/2, FlxG.width/4);
		bScore.setSize(20);
		bScore.setAlignment("center");
		add(bScore);
		
		score = new FlxText(FlxG.width/4, FlxG.height/2,  FlxG.width/2);
		score.setSize(30);
		score.setAlignment("center");
		
		add(gameOver);
		add(message);
		add(score);
	}
	
	@Override
	public void update(){
		time += FlxG.elapsed;
		FlxG.log(time);
		
		if (time > 2){
			gameOver.kill();
			message.setText("YOUR SCORE:");
		}
		if (time > 3){
			score.setText(score2+"");
			if (score2 < FlxG.score){
                if (FlxG.score - score2 > 100){
                    score2 += 100;
                } else {
                    score2 += 1;
                }
			}else if (score2 >= FlxG.score){
				score.setText(FlxG.score+"");
				score.setSize(40);
                if (bestScore == FlxG.score) {
                    bScore.setText("New\nBest");
                } else {
                    bScore.setText(bestScore + "");
                }
				if (FlxG.mouse.pressed()){
					returnMenu();
				}
			}
		}
	}
	
	private void returnMenu(){
		FlxG.switchState(new MenuState());
	}
}
