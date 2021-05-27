data = read.csv("/Users/filippo/Downloads/query2-test2-report+csv/query2-test2.csv")

startIndex = 25
endIndex = 2400
samplingInterval =  1 # seconds
experimentDuration = (endIndex - startIndex - 1) * samplingInterval

N = 1
X = 1.8
totRequestSubmitted = X * experimentDuration # slightly wrong (since wthe throughput considers the entire duration of the experiment)
totDiskDepartures = sum(data$X..DESKTOP.LSAC3CB.Disco.fisico._Total..Letture.disco.sec[startIndex:endIndex] ) * samplingInterval
system.R = 0.45

disk.U_t = 1 - (data$X..DESKTOP.LSAC3CB.Disco.fisico._Total....Tempo.inattivit[startIndex:endIndex] / 100)
disk.U = mean(disk.U_t)
disk.D = disk.U / X # (by the utilisation law and the forced flow law) Notes 3.2.3
disk.e = totDiskDepartures / totRequestSubmitted # Estimating RVR as explained in the assignment
disk.avgServiceTime = disk.D / disk.e  # From SD definition
# disk.avgServiceTime2 = 

cpu.U_t = 1 - (data$X..DESKTOP.LSAC3CB.Processore._Total....Tempo.inattivit[startIndex:endIndex] / 100)
cpu.U = mean(cpu.U_t)
cpu.D = cpu.U / X
cpu.e = 1 # assumption
cpu.avgServiceTime = system.R - (disk.e/cpu.e) * disk.avgServiceTime # Suggested by Marin (and also from the paper)
# cpu.avgServiceTime2 = cpu.D / cpu.e




