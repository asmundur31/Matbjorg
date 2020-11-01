# Matbjörg
Þetta verkefni er að stuðla gegn matarsóun á Íslandi með því að gefa mat annað tækifæri í neyslu.
## Keyra verkefnið
Við ætlum að gera ráð fyrir að verkefnið sé keyrt í forritunarumhverfinu IntelliJ IDEA.
1. Sækja IntelliJ IDEA
2. Setja upp PostgreSQL gagnagrunn á port 5432.
    1. Leiðbeiningar fyrir Windows [PostgreSQL Windows](https://www.postgresqltutorial.com/install-postgresql/).
    2. Leiðbeiningar fyrir MacOs [PostgreSQL MacOs](https://www.postgresql.org/download/macosx/), þið ráðið hvort þið 
    sækið appið eða installer-inn.
3. Opna verkefnið í IntelliJ IDEA
4. Passa að öll Maven dependencies eru sótt sem eru skilgreind í pom.xml skránni.
5. Passa að rétt username sé skilgreint í application.properties skránni.
6. Nú er allt klárt til að keyra verkefnið og hægt er að smella á 'Run'.

Ef þið viljið nota annan gagnagrunn en PostgreSQL þá þurfið þið að passa að setja viðeigandi dependencies í pom.xml
skránna og breyta application.properties skránni á viðeigandi hátt.
## Vinnuskipulag
1. Þegar við erum að vinna í verkefninu þá búum við til nýtt branch út frá dev með viðeigandi nafni fyrir verkið.
2. Þegar við erum sátt með lausnina okkar þá búum við til pull request á dev.
3. Þegar einhver hefur búið til pull request þá reynum við að leysa það sem fyrst.
4. Þegar við ætlum að merge-a við master þá reynum við að vera flest saman.
