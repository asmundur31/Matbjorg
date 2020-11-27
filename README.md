# Matbjörg
Þetta verkefni er að stuðla gegn matarsóun á Íslandi með því að gefa mat annað tækifæri í neyslu.

## Keyra verkefnið á local gagnagrunni
Athugum að þegar local gagnagrunnur er settur upp þá eru engin gögn inn í honum og þá er síðan frekar tómleg.

Við ætlum að gera ráð fyrir að verkefnið sé keyrt í forritunarumhverfinu IntelliJ IDEA.
1. Sækja IntelliJ IDEA.
2. Setja upp PostgreSQL gagnagrunn á port 5432.
    1. Leiðbeiningar fyrir Windows [PostgreSQL Windows](https://www.postgresqltutorial.com/install-postgresql/).
    2. Leiðbeiningar fyrir MacOs [PostgreSQL MacOs](https://www.postgresql.org/download/macosx/), þið ráðið hvort þið 
    sækið appið eða installer-inn.
3. Opna verkefnið í IntelliJ IDEA.
4. Passa að öll Maven dependencies eru sótt sem eru skilgreind í pom.xml skránni.
5. Passa að rétt nafn sé skilgreint í application.properties skránni, þ.e. nafnið á gagnagrunninum.
6. Passa að rétt username sé skilgreint í application.properties skránni, þ.e. userinn sem þið eruð inná.
7. Ekki er nauðsynlegt að breyta lykilorðinu í application.poperties skránni.
8. Nú er allt klárt til að keyra verkefnið og hægt er að smella á 'Run'.

Ef þið viljið nota annan gagnagrunn en PostgreSQL þá þurfið þið að passa að setja viðeigandi dependencies í pom.xml
skránna og breyta application.properties skránni á viðeigandi hátt.

## Keyra verkefnið á remote gagnagrunni
Nokkrar athugasemdir áður en tenging við remote gagnagrunnin er sett upp.
1. Það eru eitthver gögn inn á gagnagrunninum
2. Auglýsingar sem eru þar inni gætu verið útrunnar þegar þið tengist honum og þá sjást engar auglýsingar á síðunni.
3. Við geymum aðeins nöfn mynda í gagnagrunninum, þannig að enginn mynd mun birtast nema þið hafið myndir með viðeigandi
nöfn í möppunni src/main/resources/static/img/advertisementImages í verkefna skipulaginu á ykkar tölvu.
4. Notað er Heroku til að setja upp gagnagrunninn og þeir hámarka 20 tengingar við hann. JPA heldur utan um pool af 
tengingum svo í raun er ein tenging jafngild 10 tengingum. Það þýðir að aðeins tveir geta tengst í einu.

Af þessum athugasemdum sést að þetta er frekar takmarkað og eru þetta ástæðurnar fyrir því að við ákváðum að nota ekki
remote gagnagrunn í kynninguni. En ykkur er velkomið að prófa að tengjast.

Við gerum ráð fyrir eins og áður að verkefnið sé keyrt í IntelliJ IDEA.
1. Sækja IntelliJ IDEA.
2. Opna verkefnið í IntelliJ IDEA.
3. Passa að öll Maven dependencies eru sótt sem eru skilgreind í pom.xml skránni.
4. Setjið eftirfarnadi kóðalínur í application.properties í staðinn fyrir sambærilegar línur þar:
    ```
    spring.datasource.url=jdbc:postgresql://ec2-54-205-248-255.compute-1.amazonaws.com:5432/dcl712pkc92os7
    spring.datasource.username=jnnnqrzurvsecy
    spring.datasource.password=7464dbf02bdbdff94606bd136abc3c9625b4980d180343aeba1aaf815218cfb6
    ```
5. Nú er allt klárt til að keyra verkefnið og hægt er að smella á 'Run'.

## Vinnuskipulag
1. Þegar við erum að vinna í verkefninu þá búum við til nýtt branch út frá dev með viðeigandi nafni fyrir verkið.
2. Þegar við erum sátt með lausnina okkar þá búum við til pull request á dev.
3. Þegar einhver hefur búið til pull request þá reynum við að leysa það sem fyrst.
4. Þegar við ætlum að merge-a við master þá reynum við að vera flest saman.
