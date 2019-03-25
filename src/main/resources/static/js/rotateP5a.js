var red,Bgreen,blue,orange,Dgreen,yellow,skyBlue,purple;
var colordata = [];

//var freq =$('#demo').data('array');// <div id = "demo" data-array='[5,4,6,7,5,4,6,4]'>
var freq = [0,0,0,0,0,0,0,0];
//var freq = [6,4,2,6,3,5,8,3]; //need to normalize data between 1 and 10
var startAngle = 0;
var endAngle = 0;
var a = 1; var bc = 1,ab=1, cd = 1;
var de = 1, ef = 1;
var fg = 1; gh = 1;
var i=0,as = 1,inc = 2;
var theta = 0;
var cx = 0;//800/2;
var cy = 0;//800/2;
var dis = 0;

var minD = 0;
var maxD = 0;


//SETUP FUNCTION

function setup() {
  canvas = createCanvas(800, 800);
  endAngle = PI/4;

  freq = user;
  minD = min(user);
  maxD = max(user);

  print(user);
//(x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
 freq = user.map(x => (x - minD)*((12-3)/(maxD-minD))+3);

 for(let xx = 0; xx < 8; xx++){
    if(user[xx] == 0){
       freq[xx] = 0;
    }
 }
  print("after :");
  print(freq);

 	red = color('#ea4335');
  Bgreen = color('#74ed83');
  blue = color('#0059c1');

  orange = color('#f57d00'); 
  Dgreen = color('#008926');
  yellow = color('#fbbc05');
  
  skyBlue = color('#80b4ff');
  purple = color('#9c27b0');
             //red=2, orange=1, blue=2, bright_green=2, dark_green=1, yellow=2, purple=1, sky_blue=0
  colordata = [red,orange,blue,Bgreen,Dgreen,yellow,purple,skyBlue];
  noStroke();
  //stroke(250,120);
  strokeWeight(3);
  for(let ix = 0; ix < 8; ix++){
    freq[ix] = (freq[ix])*60;
  }
  inc = (max(freq)-min(freq))/60;
}


// DRAW FUNCTION

function draw() {
  translate(400,400);
  background(255,0);
 // fill(0);
 // text(user,100,100);

  theta = atan2(mouseY-400,mouseX-400); //move the global origin to the center of the drawing!
  dis = sqrt(pow(mouseX-400,2)+pow(mouseY-400,2));
  
  //maybe it is removed
  fill(230,100);
  ellipse(cx,cy,as,as);
  
  //red
  if(a < freq[0]-freq[0]/6){a+=inc;}
//dark
  fill(color('#b0000c'));
  arc(cx,cy,a+freq[0]/6,a+freq[0]/6,startAngle,endAngle);
//original
  fill(colordata[0]);  
  arc(cx,cy,a,a,startAngle,endAngle);
  
  //orange
   if(ab < freq[1]-freq[1]/6){ab+=inc;}
   //dark
   fill(color('#bb4e00'));
   arc(cx,cy,ab+freq[1]/6,ab+freq[1]/6,PI/4,PI/2);
   //original
   fill(colordata[1]);
   arc(cx,cy,ab,ab,PI/4,PI/2);
   
  //blue 
  if(bc < freq[2]-freq[2]/6){bc+=inc;}
  //dark
  fill(color('#003190'));
   arc(cx,cy,bc+freq[2]/6,bc+freq[2]/6,PI/2,PI*3/4);
  //original
  fill(colordata[2]);
   arc(cx,cy,bc,bc,PI/2,PI*3/4);
  
 //Bgreen
  if(cd < freq[3]-freq[3]/6){cd+=inc;}
//dark
  fill(color('#3cba54'));
  arc(cx,cy,cd+freq[3]/6,cd+freq[3]/6,PI*3/4,PI);
//original
  fill(colordata[3]);
   arc(cx,cy,cd,cd,PI*3/4,PI);
  
  //DarkGreen
  if(de < freq[4]-freq[4]/6){de+=inc;}
  //dark
  fill(color('#005a00'));
  arc(cx,cy,de+freq[4]/6,de+freq[4]/6,PI,PI+PI/4);

  //original
  fill(colordata[4]);
   arc(cx,cy,de,de,PI,PI+PI/4);
  
  //Yellow
  if(ef < freq[5]-freq[5]/6){ef+=inc;}
  //dark
  fill(color('#c99a02'));
  arc(cx,cy,ef+freq[5]/6,ef+freq[5]/6,PI+PI/4,PI+PI/2);
  //original
   fill(colordata[5]);
   arc(cx,cy,ef,ef,PI+PI/4,PI+PI/2);
  
//purple
  if(fg < freq[6]-freq[6]/6){fg+=inc;}
  //dark
  fill(color('#6a0080'));
  arc(cx,cy,fg+freq[6]/6,fg+freq[6]/6,PI+PI/2,PI+PI*3/4);
  //original
   fill(colordata[6]);
   arc(cx,cy,fg,fg,PI+PI/2,PI+PI*3/4);
  
 //Sky-Blue
  if(gh < freq[7]-freq[7]/6){gh+=inc;}
  fill(color('#4a85cb'));
  arc(cx,cy,gh+freq[7]/6,gh+freq[7]/6,PI+PI*3/4,2*PI);
  //original
   fill(colordata[7]);
   arc(cx,cy,gh,gh,PI+PI*3/4,2*PI);


  fill(255,129);
  ellipse(cx,cy,92,92);
  fill(255);
  ellipse(cx,cy,80,80);
  
  
  if(as < max(freq)){
    as = as + inc*3.5;
  }

  textStyle(BOLD);
  textSize(18);
  textAlign(CENTER,CENTER);
  
// MOUSE OVER EFFECT

  if(theta > 0 && theta < PI/4 && (dis > 60 && dis < 300)){
    
    fill(color('#b0000c'));
      arc(cx,cy,a+freq[0]/6,a+freq[0]/6,startAngle,endAngle);
  
    fill(255,129);
    ellipse(cx,cy,128,128);
    fill(255);
    ellipse(cx,cy,120,120);
  
    fill(colordata[0]);
    text('ANGER', cx,cy);
  }
  else if(theta > PI/4 && theta < PI/2 && (dis > 60 && dis < 300)){
    
    fill(color('#bb4e00'));
   arc(cx,cy,ab+freq[1]/6,ab+freq[1]/6,PI/4,PI/2);

    fill(255,129);
    ellipse(cx,cy,128,128);
    fill(255);
    ellipse(cx,cy,120,120);

    fill(colordata[1]);
    text('OPTIMISTIC', cx,cy);
  }
  else if(theta > PI/2 && theta < PI*3/4 && (dis > 60 && dis < 300)){

    fill(color('#003190'));
   arc(cx,cy,bc+freq[2]/6,bc+freq[2]/6,PI/2,PI*3/4);
    
    fill(255,129);
    ellipse(cx,cy,128,128);
    fill(255);
    ellipse(cx,cy,120,120);

    fill(colordata[2]);
    text('SADNESS', cx,cy);
  }

  else if(theta > PI*3/4 && theta < PI && (dis > 60 && dis < 300)){

    //dark
  fill(color('#3cba54'));
  arc(cx,cy,cd+freq[3]/6,cd+freq[3]/6,PI*3/4,PI);
    
    fill(255,129);
    ellipse(cx,cy,128,128);
    fill(255);
    ellipse(cx,cy,120,120);

    fill(colordata[3]);
    text('TRUST', cx,cy);
  }

  else if(theta > -PI && theta < (-PI+PI/4) && (dis > 60 && dis < 300)){
   
     //dark
  fill(color('#005a00'));
  arc(cx,cy,de+freq[4]/6,de+freq[4]/6,PI,PI+PI/4);

    fill(255,129);
    ellipse(cx,cy,128,128);
    fill(255);
    ellipse(cx,cy,120,120);

    fill(colordata[4]);
    text('FEAR', cx,cy);
  }

  else if(theta > (-PI+PI/4) && theta < (-PI+PI/2) && (dis > 60 && dis < 300)){

      //dark
  fill(color('#c99a02'));//darkyellow '#c38c00'
  arc(cx,cy,ef+freq[5]/6,ef+freq[5]/6,PI+PI/4,PI+PI/2);
    
    fill(255,129);
    ellipse(cx,cy,128,128);
    fill(255);
    ellipse(cx,cy,120,120);

    fill(colordata[5]);
    text('JOY', cx,cy);
  }
  
  else if(theta > (-PI+PI/2) && theta < (-PI+PI*3/4) && (dis > 60 && dis < 300)){
    
     //dark
  fill(color('#6a0080'));
  arc(cx,cy,fg+freq[6]/6,fg+freq[6]/6,PI+PI/2,PI+PI*3/4);

    fill(255,129);
    ellipse(cx,cy,128,128);
    fill(255);
    ellipse(cx,cy,120,120);

    fill(colordata[6]);
    text('DISGUST', cx,cy);
  }

  else if(theta > (-PI+PI*3/4)  && theta < 0 && (dis > 60 && dis < 300)){

    fill(color('#4a85cb'));
    arc(cx,cy,gh+freq[7]/6,gh+freq[7]/6,PI+PI*3/4,2*PI);
    
    fill(255,129);
    ellipse(cx,cy,128,128);
    fill(255);
    ellipse(cx,cy,120,120);

    fill(colordata[7]);
    text('SURPRISE', cx,cy);
  }

  print(user);

}

