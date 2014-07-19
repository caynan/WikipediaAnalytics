#!/bin/bash
: '
Script para executar o experimento de memória compartilhada
Necessita de 3 parametros
1º - Nome  do programa Java a ser executado
2º - Numero de Threads que vao executar
3º - Diretorio de arquivos
'

if [ $# != 3 ]; then
   echo "You should pass 3 arguments:"
   echo "JavaProgramaName NuberOfThreads DirecotryFiles"
else
   echo "Running $1"
:'
Serao realizadas 10 replicacoes para cada experimento
O tempo de cada execucao sera salvo em um arquivo para a analise
'
   for((i=1;i<=10;i++))
      do
         echo "Output of Iteration $i" 
         java $1 $2 $3 >> "Out_$2Threads_$3.txt"
      done
 
fi
