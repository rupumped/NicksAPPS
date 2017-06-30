clear;clc;close all
t=0:1e-5:.5;

%%
close all
f0 = 990;
f1 = 1000;
x0 = sin(2*pi*f0*t);
x1 = sin(2*pi*f1*t);
x = x0+x1;
figure
subplot(1,2,1)
plot(t,x)
[f,fdata] = ssAmpSpec(t,fft(x));
subplot(1,2,2)
plot(f,fdata)

w0 = mean([f0,f1]);
w1 = diff([f0,f1])/2;
y0 = sin(2*pi*w0*t);
y1 = sin(2*pi*w1*t);
y = 2*y0.*y1;
figure
subplot(1,2,1)
plot(t,y)
[f,fdata] = ssAmpSpec(t,fft(y));
subplot(1,2,2)
plot(f,fdata)

%%
close all

f0 = 995;
f1 = 5;
y0 = sin(2*pi*f0*t);
y1 = sin(2*pi*f1*t);
y = 2*y0.*y1;
figure
subplot(1,2,1)
plot(t,y)
[f,fdata] = ssAmpSpec(t,fft(y));
subplot(1,2,2)
plot(f,fdata)

w0 = f0-f1;
w1 = f0+f1;
x0 = sin(2*pi*w0*t);
x1 = sin(2*pi*w1*t);
x = x0+x1;
figure
subplot(1,2,1)
plot(t,x)
[f,fdata] = ssAmpSpec(t,fft(x));
subplot(1,2,2)
plot(f,fdata)

%%
clear;clc;close all
Fs = 1e3;
t=(0:1/Fs:1)+0.0064652;

f0 = 0.931e6;
f1 = 38;
y0 = sin(2*pi*f0*t);
y1 = sin(2*pi*f1*t);
y = y0.*y1;

n = wgn(1,numel(t),1);
y = y + n;

figure
subplot(1,2,1)
plot(t,y)
[f,fdata] = tsAmpSpec(t,fft(y));
subplot(1,2,2)
plot(f,fdata)
% xlim([0,100])

w0 = f0-f1;
w1 = f0+f1;