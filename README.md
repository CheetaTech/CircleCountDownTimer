## CircleCountDownTimer

This is a custom view that contains a count down timer with a circle view. With this component, 
user can get more efficient ui experience.

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
