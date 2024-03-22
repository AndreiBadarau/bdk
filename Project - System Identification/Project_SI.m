%%proiect IS
close all; clc;

t = Badarau(:,1);
u = Badarau(:,2);
y = Badarau(:,3);
y2 = Badarau(:,4);
plot(t,u,t,y), legend('u','y'), title('Date initiale');xlabel('Timp[s]'),ylabel( ...
    'Amplitudine[V]')

i1u = 101;
i2u = 111;
i1y = 104;
i2y = 115;

k = mean(y)/mean(u); % factor de proportionalitate
T1 = 2*(t(i2u) - t(i1u)); % perioada de rezonanta

Mr = ((y(i2y) - y(i1y))/k)/((u(i2u) - u(i1u))/k); % modul de rezonanta; amplificare maxima
wr = 2*pi/T1; % pulsatia de rezonanta
zeta = sqrt((Mr - sqrt(Mr^2 - 1))/2/Mr)/k; % factor de amortizare
wn = wr/sqrt(1-2*zeta^2); % pulsatia naturala de oscilatie

num = k*wn^2;
den = [1, 2*zeta*wn,wn^2];
H = tf(num, den) 

A = [0 1;
     -wn^2 -2*zeta*wn];
B = [0; 
     k*wn^2];
C = [1 0];
D = 0;

sys = ss(A,B,C,D);
yc = lsim(sys, u, t, [y(1);(y(2)-y(1))/(t(2)-t(1))]);
figure, plot(t,[y,yc]), legend('iesirea','iesirea simulata');xlabel('Timp[s]'),ylabel( ...
    'Amplitudine[V]')

J = 1/sqrt(length(t))*norm(y-yc)
empn = norm(y-yc)/norm(y-mean(y))*100 %eroare medie patratica

Te = t(2) - t(1);

data_id = iddata(y, u, Te);
data_vd = iddata(y, u, Te);

%% model ARMAX - e ok

model_armax = armax(data_id, [2 2 2 1])
subplot(211), resid(data_vd, model_armax);
subplot(212), compare(data_vd, model_armax);

%%
H_armax = tf(model_armax)
H_armax_c = d2c(H_armax, 'zoh')

%% model OE - e ok

model_oe = oe(data_id, [2 2 1])
subplot(211), resid(data_vd, model_oe);
subplot(212), compare(data_vd, model_oe);

%%
H_oe = tf(model_oe)