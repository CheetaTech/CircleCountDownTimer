## CircleCountDownTimer

This is a custom view that contains a count down timer with a circle view. With this component, 
user can get more efficient ui experience.

![ColorHub Screenshots](/img/videotogif_2016.12.14_19.05.04.gif) 

## Features

  * Start and stop timer when you want
  * You can use speedUp, speedDown or reset features for various usage like percentage,progressing state etc. 
    We will continue to develop and add this features soon

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

 
