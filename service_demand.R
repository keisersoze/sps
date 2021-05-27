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

# Searching evidence of instability

disk.lambda_t = data$X..DESKTOP.LSAC3CB.Disco.fisico._Total..Letture.disco.sec[startIndex:endIndex] # Under stability the arrival rate is equal to the service rate
disk.lambda = mean (disk.lambda_t) # Assuming that arrival/departures are iid 
disk.U_t = 1 - (data$X..DESKTOP.LSAC3CB.Disco.fisico._Total....Tempo.inattivit[startIndex:endIndex] / 100)
disk.mu_t = disk.lambda_t / disk.U_t # Utilization law
disk.mu = mean (disk.mu_t)
disk.avgServiceTime = 1 / disk.mu
disk.e = totDiskDepartures / totRequestSubmitted
disk.X = disk.e * X # Forced flow law: sanity check for disk.lambda
disk.D = disk.e * disk.avgServiceTime

cpu2.e = 100
cpu2.X = cpu2.e * X # Forced flow law
cpu2.lambda = cpu2.X # Assuming stability
cpu2.U_t = 1 - (data$X..DESKTOP.LSAC3CB.Processore._Total....Tempo.inattivit[startIndex:endIndex] / 100)
cpu2.U = mean(cpu2.U_t)
cpu2.mu_t = cpu2.lambda / cpu2.U_t # Utilization law
cpu2.mu = mean (cpu2.mu_t)
cpu2.avgServiceTime = 1 / cpu2.mu
cpu2.D = cpu2.e * cpu2.avgServiceTime

cpu.e = 1
cpu.avgServiceTime = system.R - (disk.e/cpu.e) * (1/disk.mu) # Formula from paper
cpu.mu = 1/cpu.avgServiceTime
cpu.D = cpu.e * cpu.avgServiceTime
                









