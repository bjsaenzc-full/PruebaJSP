#!/usr/bin/env python
import numpy as np
import pandas as pd

# ------------------- #
# -- Lee los datos -- #
# ------------------- #
def lectura(archivo):
    rawData = pd.read_excel(archivo, header = 1);
    return rawData

# ---------------------------------------------------------- #
# -- Extrae las columnas del archivo de excel con nombres -- #
# ---------------------------------------------------------- #
def extraccionColumnas(rawData):
    col = rawData.columns
    columns = dict()
    for i in enumerate(col):
        columns[i[0]] = i[1]
    return columns

# -------------------------------------------------------------- #
# -- Genera el conjunto de datos que poblará la base de datos -- #
# -------------------------------------------------------------- #
def genracionDatos(rawData):
    numeroFilas = len(rawData)
    columns = extraccionColumnas(rawData)
    nombres = rawData[columns[0]]
    # --> Genera tasas de interés uniformemente distribuidas en el intervalo (1.01, 2.01)
    #   haciendo redondeo a dos digitos decimales
    interes = pd.Series(np.round(np.random.random(numeroFilas) + 1.01, 2))
    # --> Genera los montos disponibles (de cada socio) como números enteros
    #   uniformemente distribuidos en el intervalo [10^6, 15^6]
    monto = pd.Series(np.random.randint(1e6, 15e6, numeroFilas))
    # --> Genera un diccionario con esos tres conjuntos de datos (nombres, tasa de
    #   interes, monto), para posteriormente generar el script de SQL que llenará
    #   la base de datos
    sociosPrevio = {'nombre':nombres, 'tasaDeInteres':interes, 'montoMaxDisp': monto}
    # --> Genera el DataFrame con el diccionario anterior
    socios = pd.DataFrame(sociosPrevio)
    return socios

def generacionSQL(socios):
    # Crea el archivo *.sql con las 'queries' para poblar la base de datos
    f = open('llenar_socios2.sql', 'w')
    # Genera las 'queries'
    for i in range(len(socios)):
        x = "INSERT INTO socios.datos (socioID, nombre, tasaDeInteres, montoMaxDisp) VALUES ({id}, '{Nombre}', '{interes:.2f}', '{monto}');\n".format(id = i + 1, Nombre = socios['nombre'][i], interes = socios['tasaDeInteres'][i], 
            monto = socios['montoMaxDisp'][i])
        f.write(x)
    f.close()

if __name__ == '__main__':
    rawData = lectura('Top1000.xls')
    socios = genracionDatos(rawData)
    generacionSQL(socios)
