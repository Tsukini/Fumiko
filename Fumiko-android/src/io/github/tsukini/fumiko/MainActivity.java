package io.github.tsukini.fumiko;

import org.flixel.FlxAndroidApplication;

public class MainActivity extends FlxAndroidApplication 
{
    public MainActivity()
	{
		super(new FumikoGame());
	}
}