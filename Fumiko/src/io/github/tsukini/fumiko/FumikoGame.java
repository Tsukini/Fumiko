package io.github.tsukini.fumiko;

import org.flixel.FlxGame;
import org.flixel.FlxG;

public class FumikoGame extends FlxGame
{
	public FumikoGame()
	{
		super(400, 240, MainState.class, 2, 50, 50, false);
	}
}
