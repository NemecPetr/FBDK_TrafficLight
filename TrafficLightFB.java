/* Copyright (c)2026 Holobloc Inc. All rights reserved. */
package fb.rt.test;
import fb.datatype.*;
import fb.rt.*;
/** FUNCTION_BLOCK TrafficLightFB (* Task 1 -  Function Block Type *)
  * @author JHC
  * @version 20260317/JHC - Generated.
  */
public class TrafficLightFB extends fb.rt.FBInstance {
/** The index (0) of state START. */
public static final int INDEX_START = 0;
/** The index (1) of state INIT. */
public static final int INDEX_INIT = 1;
/** The index (2) of state Stop. */
public static final int INDEX_Stop = 2;
/** The index (3) of state GetReady. */
public static final int INDEX_GetReady = 3;
/** The index (4) of state Go. */
public static final int INDEX_Go = 4;
/** The index (5) of state StopIfSave. */
public static final int INDEX_StopIfSave = 5;
/** Initialization Confirm */
public final EventOutput INITO = new EventOutput();
/** Execution Confirmation */
public final EventOutput CNF = new EventOutput();
/** EVENT TimeStart */
public final EventOutput TimeStart = new EventOutput();
/** Initialization Request */
public final EventServer INIT = (e) -> service_INIT();
/** Normal Execution Request */
public final EventServer REQ = (e) -> service_REQ();
/** EVENT TimeOut */
public final EventServer TimeOut = (e) -> service_TimeOut();
/** VAR Mode:UINT */
  public UINT Mode = new UINT();
/** VAR TGreen:TIME */
  public TIME TGreen = new TIME();
/** VAR TYellow:TIME */
  public TIME TYellow = new TIME();
/** Output event qualifier */
  public final BOOL QRed = new BOOL();
/** VAR QYellow:BOOL */
  public final BOOL QYellow = new BOOL();
/** VAR QGreen:BOOL */
  public final BOOL QGreen = new BOOL();
/** VAR CurrentDelay:TIME */
  public final TIME CurrentDelay = new TIME();
/** The default constructor. */
public TrafficLightFB(){
    super();
  }
protected synchronized void service_INIT(){
  if(eccState == INDEX_START){
    state_INIT();
  }
}
protected synchronized void service_REQ(){
  if((eccState == INDEX_START) && (Mode.value==0)){
    state_Stop();
  }
  else if((eccState == INDEX_START) && (Mode.value==1)){
    state_GetReady();
  }
  else if((eccState == INDEX_START) && (Mode.value==2)){
    state_Go();
  }
  else if((eccState == INDEX_START) && (Mode.value==3)){
    state_StopIfSave();
  }
}
protected synchronized void service_TimeOut(){
  if(eccState == INDEX_Stop){
    state_START();
  }
  else if(eccState == INDEX_GetReady){
    state_START();
  }
  else if(eccState == INDEX_Go){
    state_START();
  }
  else if(eccState == INDEX_StopIfSave){
    state_START();
  }
}
/** The actions to take upon entering state START. */
void state_START(){
   eccState = INDEX_START;
}
/** The actions to take upon entering state INIT. */
void state_INIT(){
   eccState = INDEX_INIT;
   alg_INIT();
   INITO.serviceEvent(this);
   state_START();
}
/** The actions to take upon entering state Stop. */
void state_Stop(){
   eccState = INDEX_Stop;
   alg_AlgStop();
   TimeStart.serviceEvent(this);
}
/** The actions to take upon entering state GetReady. */
void state_GetReady(){
   eccState = INDEX_GetReady;
   alg_AlgGetReady();
   CNF.serviceEvent(this);
}
/** The actions to take upon entering state Go. */
void state_Go(){
   eccState = INDEX_Go;
   alg_AlgGo();
   CNF.serviceEvent(this);
}
/** The actions to take upon entering state StopIfSave. */
void state_StopIfSave(){
   eccState = INDEX_StopIfSave;
   alg_AlgStopIfSave();
   CNF.serviceEvent(this);
}
  /** ALGORITHM INIT IN ST
 * Initialization algorithm
 */
public void alg_INIT(){
}
  /** ALGORITHM AlgStop IN ST */
public void alg_AlgStop(){
QRed.value =true;
QYellow.value = false;
QGreen.value = false;

CurrentDelay.value = TGreen.value;
}
  /** ALGORITHM AlgGetReady IN ST */
public void alg_AlgGetReady(){
QRed.value = true;
QYellow.value = true;
QGreen.value = false;

CurrentDelay.value = TYellow.value;
}
  /** ALGORITHM AlgGo IN ST */
public void alg_AlgGo(){
QRed.value = false;
QYellow.value = false;
QGreen.value = true;
}
  /** ALGORITHM AlgStopIfSave IN ST */
public void alg_AlgStopIfSave(){
QRed.value = false;
QYellow.value = true;
QGreen.value = false;

CurrentDelay.value = TYellow.value;
}
}
