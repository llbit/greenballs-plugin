# This Is Fine Plugin for Jenkins

![This is Fine](https://img.shields.io/badge/build-fine-brightgreen.svg)

This plugin replaces all build status indicators with dogs and fires
inspired by the [This Is Fine meme](https://knowyourmeme.com/memes/this-is-fine).

## Preview

This is what your Jenkins will look like with the plugin:

![screenshot](https://raw.githubusercontent.com/llbit/thisisfine-plugin/master/thisisfine.png)

* Happy dog = success.
* Dog with fire in the background = test failures.
* Only fire = build failed.
* Gray dog = not built.

## Installing

1. Download the latest packaged plugin [from this link.](https://github.com/llbit/thisisfine-plugin/releases/download/thisisfine-1.0/thisisfine.hpi)
2. Navigate to `Jenkins->Manage Jenkins->Manage Plugins->Advanced`.
3. Upload `thisisfine.hpi` under "Upload Plugin".
4. Make sure to restart Jenkins for changes to take effect.

## Building

To build the plugin from source: `mvn package`. On success the `.hpi` will be
output to `target/thisisfin.hpi`.
