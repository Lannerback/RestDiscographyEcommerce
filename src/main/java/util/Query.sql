/* Prendere il nome e il cognome dell'artista che ha più album */
SELECT Artist.name,Artist.surname,MAX(A.num)
	FROM (SELECT artist_uid,count(artist_uid) as num FROM Album GROUP BY artist_uid ) A join Artist on A.artist_uid=Artist.uid;


