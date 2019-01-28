# This Is Fine Plugin for Jenkins

This plugin replaces all build status indicators with dogs and fires
inspired by the [This Is Fine meme](https://knowyourmeme.com/memes/this-is-fine).

This plugin was forked from the green balls plugin.

## Installing

1. Download the latest packaged plugin [from this link.](https://github.com/llbit/thisisfine-plugin/releases/download/thisisfine-1.0/thisisfine.hpi)
2. Install by going to `Jenkins->Manage Jenkins->Manage Plugins->Advanced`. Upload
the `.hpi` file under "Upload Plugin". Make sure to restart Jenkins for changes
to take effect.

## Preview

This is what your Jenkins will look like with the plugin:

![screenshot](https://raw.githubusercontent.com/llbit/thisisfine-plugin/master/thisisfine.png)

* Happy dog = success.
* Dog with fire in the background = test failures.
* Only fire = build failed.
* Gray dog = not built.
