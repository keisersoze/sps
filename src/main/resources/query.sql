
SELECT F.DEP_DELAY, F.ARR_DELAY
FROM Fligth F
WHERE F.FLIGHT_DATE == ? AND F.OP_CARRIER_FL_NUM == ?

SELECT F.ID, F.DATA(!), F.ORIGIN_CITY_NAME, F.DEST_CITY_NAME
FROM Fligth F
WHERE  F.DELAY(!!) > ? AND (?)< F.FLIGHT_DATE < (?)

departure delay  +-
arrival delay +-

SELECT F.ORIGIN_AIRPORT_ID,
    (SELECT COUNT(F.ORIGIN_AIRPORT_ID)
    FROM Fligth F2
    WHERE F2.ORIGIN_AIRPORT_ID == F.ORIGIN_AIRPORT_ID AND F.DEP_DELAY > 0
    GROUP BY F.ORIGIN_AIRPORT_ID)
    /
    COUNT(F.ORIGIN_AIRPORT_ID)
FROM Fligth F
GROUP BY F.ORIGIN_AIRPORT_ID

SELECT F.ORIGIN_AIRPORT_ID
FROM Fligth F
GROUP BY F.ORIGIN_AIRPORT_ID
ORDER BY SUM(CASE WHEN F.DEP_DELAY > (?) THEN 1 ELSE 0 END)/COUNT(*)
LIMIT (?)

// Query utilizzate

SELECT f.arr_delay, f.dep_delay
FROM flights f
WHERE f.op_carrier_fl_num = :flightNumber AND f.fl_date = :flightDate

SELECT f.id, f.fl_date, f.origin_city_name, f.dest_city_name
FROM flights f
WHERE f.arr_delay > :minDelay AND f.fl_date between :lowerDate and :upperDate

SELECT f.origin_airport_id
FROM flights f
WHERE f.fl_date between :lowerDate and :upperDate
GROUP BY f.origin_airport_id
ORDER BY CAST(SUM(CASE WHEN f.dep_delay > 0 THEN 1 ELSE 0 END) AS FLOAT)/COUNT(*) DESC
LIMIT :numReturn