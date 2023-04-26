echo "practica,num_hilos,tiempo_real,tiempo_sys,tiempo_usuario,tiempo_run" > output.csv

for i in 1 2 3 4 5
do
    # java mi_programa >> output.csv
    echo "practica1,$i,1,1,1,1" >> output.csv
done