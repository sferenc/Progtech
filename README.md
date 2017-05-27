# ProgTechBeadandó

Leírás: Ez egy mindenki számára ismert aknakereső játék.
Szabályok: Egy egyforma mezőkre osztott táblával indul a játék, ezek alatt rejtőzködnek az aknák. A tábla mérete és az aknák
száma a nehézségi szinttől függően változik. A mezők állapotai a következők lehetnek:

lefedett (alaphelyzet),
feltárt, szomszédos aknával,
feltárt aknamentes,
zászlós (véleményünk szerint akna van alatta), 
feltárt, robbanó aknával (ha egy mező ilyen állapotba kerül, a játék véget ér, a játékos a menetet elvesztette). A zászlós
állapot az egér jobb gombjával érhető el, csupán segítséget nyújt a játékhoz. A játékot teljesíteni lehet anélkül is, 
hogy akár csak egy mezőt is megjelölnénk zászlóval, ez azonban a játékban szerzett jelentős gyakorlatot és az egész játékmenet 
során mindvégig komoly figyelmet igényel. Egy mezőt feltárni kattintással lehet. Ha egy mező feltárult,
és mellette akna található, akkor annak darabszámát egy számmal fogja jelezni (egy mező mellett értelemszerűen maximum 8 akna  
lehet). Ha a játékos aknamentes környezetű mezőre kattint, akkor az adott mezőhöz oldal- és sarokhatárosan csatlakozó (aknamentes)
mezők mindegyike feltárul, valamint az így feltáruló aknamentes szigettel szomszédos mezők is feltárulnak. 

A program folyamatosan jelzi a még megjelöletlen aknák számát, illetve az eltelt időt. A játék célja: teljesíteni a táblát a
lehető legrövidebb idő alatt. Ha aknára kattintunk, az adott mező \"felrobban\", tehát a játék véget ér, s az adott menetet 
elvesztettük. Győzelemmel kizárólag abban az esetben fejeződik be a játék, ha felfedtünk minden olyan mezőt, amely alatt nincs  
akna. A győzelem elérése nem függ attól, hogy hány aknát jelöltünkmeg zászlóval.
Telepítés:
Belépsz a terminállal oda ahova letöltötted az állományokat(az aknakeresog könyvtáron belülre) és beirod hogy mvn package.

Indítás:
Az aknakeresog könyvtárban ha már feltelepítetted az alkalmazást beírod, hogy mvn exec:java.
