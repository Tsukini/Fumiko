package io.github.tsukini.fumiko;

import org.flixel.*;
import org.flixel.event.IFlxButton;
import com.badlogic.gdx.utils.IntArray;

public class MenuState extends FlxState {

	private FlxSprite backgnd;
	private FlxText title, footer, bestScore;
	private FlxButton btnPlay, btnCredits, btnQuit, btnClear;
	private FlxSave gameSave;
				
	@Override
	public void create() {
		
		FlxG.shake();
		FlxG.vibrate(1200);
		
		gameSave = new FlxSave();
		gameSave.bind("fumikoSave");

        if (gameSave.data.get("scores", IntArray.class) != null){
            FlxG.scores = gameSave.data.get("scores",IntArray.class);}
										
		backgnd = new FlxSprite(0,0).loadGraphic("background.png", false, false, FlxG.width, FlxG.height);
		backgnd.scale = new FlxPoint(2,2);
		backgnd.setOriginToCorner();
		add(backgnd);
		
		title = new FlxText(FlxG.width/2 - 70, FlxG.height/5 ,140, "Fumiko");
		title.setSize(30);
		title.setAlignment("center");
		add(title);
		
		footer = new FlxText(0,0, 100, "Developed by:\nTsukini");
		footer.y = FlxG.height - footer.height;
		footer.x = FlxG.width/2 - footer.width/2;
		footer.setColor(0xD3D3D3);
		footer.setAlignment("center");
		add(footer);
		
		bestScore = new FlxText(0, 0, FlxG.width/2, "Best score: 0");
		bestScore.x = FlxG.width/4;
		bestScore.y = title.y+title.height+5;
		bestScore.setAlignment("center");
		if (FlxG.scores.size > 0)
			bestScore.setText("Best score: " + FlxG.scores.get(1));
		add(bestScore);

		btnPlay = new FlxButton(0, FlxG.height/2, "Play",new IFlxButton(){@Override public void callback(){clickPlay();}});
		btnPlay.x = FlxG.width/2 - btnPlay.width/2;
		btnPlay.label.setAlignment("center");
		add(btnPlay);
		

		btnClear = new FlxButton(0, 0, "Clear Data", new IFlxButton(){@Override	public void callback(){ saveClear(); } });
		btnClear.x = FlxG.width/2 - btnClear.width/2;
		btnClear.y = btnPlay.y + btnPlay.height + 5;
		add(btnClear);
		
		btnCredits = new FlxButton(0 ,0, "Credits", new IFlxButton(){@Override public void callback(){clickCredits();}});
		btnCredits.x = FlxG.width/2 - btnCredits.width/2;
		btnCredits.y = btnClear.y + btnClear.height + 5;
		add(btnCredits);

        btnQuit = new FlxButton(0,0, "Quit", new IFlxButton() {
            @Override
            public void callback() {
                quitGame();
            }
        });
        btnQuit.x = FlxG.width/2 - btnQuit.width/2;
        btnQuit.y = btnCredits.y + btnCredits.height + 5;
        add(btnQuit);
	}
			
	public void clickPlay(){
		FlxG.switchState(new PlayState());
	}
	
	public void clickCredits(){
		FlxG.switchState(new CreditsState());
	}
	
	private void saveClear(){
		gameSave.erase();
	}

    private void quitGame() { System.exit(0); }
}
