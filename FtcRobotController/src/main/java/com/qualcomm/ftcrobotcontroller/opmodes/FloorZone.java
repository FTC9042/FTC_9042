package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Tim on 10/25/2015.
 */
//STARTING POSITION = Middle on crack of 2 Mats from side non mountain corner

public class FloorZone extends AutonHelper{


    //establish run states for auton
    enum RunState{
        RESET_STATE,
        FIRST_STATE,
        LAST_STATE
    }


    private RunState rs = RunState.RESET_STATE;

    public FloorZone() {}


    @Override
    public void loop() {

        basicTel();
        telemetry.addData("state: ", rs);
        setToEncoderMode();
        propellerSetToEncoderMode();
        alternatePropeller(on);

        switch(rs) {
            case RESET_STATE:
            {
                setZipLinePosition(0);
                if (resetEncoders()){
                    rs= RunState.FIRST_STATE;
                }
                break;
            }
            case FIRST_STATE:
            {
                on = true;
                if (runStraight(-99,false)) {
                    rs = RunState.LAST_STATE;
                }
                break;
            }
            case LAST_STATE:
            {
                on=false;
                stop();
            }
        }
    }
}