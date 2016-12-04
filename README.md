## CircleCountDownTimer

This is a custom view that contains a count down timer with a circle view. With this component, 
user can get more efficient ui experience.

## Features

  * Select easily a color which color palette page with sliding tabs that name is **`Material, Flat, Social, Metro and HTML`**.
  * See Color's name and _HEX_ Code in GridView.
  * Copy Color _HEX_ Code to the clipboard with button that is in the right bottom on the GridView.
  * Create your _Colors_ with **`Color Picker`** page. This Hex code includes **`Alpha`** Channel.

## Code Example

        CircleProgressView circleTimer = (CircleProgressView) findViewById(R.id.circleCountDownTimer);
        circleTimer.setSecond(30);//time
        //circleTimer.setPaintStyle(Paint.Style.FILL);//fill all circle
        circleTimer.setFinishListener(new CircleProgressView.FinishListener() {
            @Override
            public void finish() {
               //when count down is finished
            }
        });
        
        circleTimer.stop();
        circleTimer.start();
        circleTimer.reset();
        circleTimer.speedUp();
        circleTimer.speedDown();
 
 ##Contributers
 
 * [Ali Güvenbaş](https://github.com/aliguvenbas)
 
 ## Collaborate

We are developing this application in public to bring you a tool that _you_ want to use. Please feel free to open [issues](https://github.com/CheetaTech/ColorHub/issues) and [contact](https://cheetatech.wordpress.com/) us for any suggestion and advice . See our [website](https://cheetatech.wordpress.com/) for more. 
