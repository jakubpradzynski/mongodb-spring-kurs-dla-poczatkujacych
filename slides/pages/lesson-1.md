# Zajęcia 1 (Operowanie bezpośrednio na bazie)

<br>
Na tych zajęciach skupimy się na operowaniu bezpośrednio na bazie MongoDB.

Nauczysz się wykonywać zapytania CRUD, wyszukiwać za pomocą bardziej skomplikowanych warunków, używać agregacji oraz tworzyć indeksy.

---

# NoSQL

1. Czym są bazy NoSQL?
2. Rodzaje baz NoSQL.
3. NoSQL vs SQL.

---
layout: two-cols
---

# MongoDB

1. Historia.
2. Cechy:
   - elastyczność
   - wysoka dostępność
   - skalowalność
   - szybkość
3. Jak przechowywane są dane?
   - JSON / BSON
   - przykład dokumentu
4. Pole `_id`.

::right::

<br><br>
```mermaid
graph TD;
    Database-->Collection-->Document;
```

---

# Replica Set

1. Czym jest?
2. Elekcja primary.

```mermaid
flowchart TB
    User --Reads--> Primary
    User --Writes--> Primary
    subgraph RS [Replica Set]
    Primary --Replication--> Secondary1
    Primary --Replication--> Secondary2
    end
```

---

# MongoDB Atlas

1. Utworzenie konta [cloud.mongodb.com](https://cloud.mongodb.com/)
2. Utworzenie bazy
3. Dodanie użytkownika
4. Dodanie adresu IP
5. Załadowanie sample data

---

# Jak podłączyć się do MongoDB?

<br>

Instalacja klient: [mongosh](https://www.mongodb.com/try/download/shell).

Connection String URI:
```bash
mongodb://user:password@localhost:27017/db?authSource=admin
```

Połaczenie:
```bash
mongosh {uri}
```

---

# Operowanie na bazie

1. `show dbs`
2. `show collections`
3. `use my_db`

---

# Operacje CRUD

1. Create - `db.collection.insert({name: "Jakub"})`
2. Read - `db.collection.find({name: "Jakub"})`
3. Update - `db.collection.update({name: "Jakub"}, {name: "Kuba"})`
4. Delete - `db.collection.delete({name: "Kuba")`

---
layout: center
title: Zadanie 1.1.1.
level: 2
---

# Zadanie 1.1.1.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
use lekcja1
```
</details>

---
layout: center
---

# Zadanie 1.1.2.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.inflacja.insertOne({"_id": 2022, "value": 113.7})
```
</details>

---
layout: center
---

# Zadanie 1.1.3.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.inflacja.insertMany([
    {"_id": 2015, "value": 99.1},
    {"_id": 2016, "value": 99.4},
    {"_id": 2017, "value": 102.0},
    {"_id": 2018, "value": 101.6},
    {"_id": 2019, "value": 102.3},
    {"_id": 2020, "value": 103.4},
    {"_id": 2021, "value": 105.1}
])
```
</details>

---
layout: center
---

# Zadanie 1.2.1.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.inflacja.find({})
```
</details>

---
layout: center
---

# Zadanie 1.2.2.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.inflacja.find({_id: 2020})
```
</details>

---
layout: center
---

# Zadanie 1.2.3.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.inflacja.find({
    $or: [
        {"value": {$lt: 101.5}},
        {"value": {$gt: 103.5}},
    ]
})
```
</details>

---
layout: center
---

# Zadanie 1.3.1.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.inflacja.updateOne(
    {"_id": 2022},
    {$set: {"value": 115.4}}
)
```
</details>

---
layout: center
---

# Zadanie 1.3.2.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.inflacja.updateMany(
    {},
    [{$set: {"value": {$subtract: ["$value", 100]}}}]
)
```
</details>

---
layout: center
---

# Zadanie 1.4.1.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.inflacja.deleteOne({_id: 2018})
```
</details>

---
layout: center
---

# Zadanie 1.4.2.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.inflacja.drop()
```
</details>

---

# Wyszukiwanie

<br>

1. Operatory logiczne, obiekty zagnieżdżone, tablice.
```
db.collection.find({$or: ["a.b": "c", "d": ["c"]]})`
```
<br>

2. Sortowanie i limitowanie wyników
```
db.collection.find({}).sort({a: 1}).limit(10)
```
<br>

3. Zliczanie
```
db.collection.count({})
```

---
layout: center
---

# Zadanie 2.1.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
use sample_mflix
db.movies.find().limit(1)
```
</details>

---
layout: center
---

# Zadanie 2.2.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.movies.find({title: "The Godfather"})
```
</details>

---
layout: center
---

# Zadanie 2.3.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.movies.countDocuments({"imdb.rating": {$gte: 9.0}})
```
</details>

---
layout: center
---

# Zadanie 2.4.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.movies.count({"languages": "Polish", "awards.wins": {$gt: 15}})
```
</details>

---
layout: center
---

# Zadanie 2.5.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.movies.find({"cast": "Henry Cavill"}, {_id: 0, title: 1}).sort({year: -1})
```
</details>

---
layout: center
---

# Zadanie 2.6.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.movies.find(
    {$or: [{"directors": ["Quentin Tarantino"]}, {"directors": ["Christopher Nolan"]}]},
    {_id: 0, title: 1, directors: 1, "imdb.rating": 1}
).sort({"imdb.rating": -1})
```
</details>

---

# Agregacje

1. Czym są agregacje (pipeline, stage)?
2. Przykład wyszukiwania

<br>

```
db.collection.aggregate([ { $match : { field : "value" } } ])
```

---
layout: center
---

# Zadanie 3.1.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.movies.aggregate([
    {$match: {title: "The Godfather"}}
])
```
</details>

---
layout: center
---

# Zadanie 3.2.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.movies.aggregate([
    {$group: {_id: "$type", count: {$sum: 1}}}
])
```
</details>

---
layout: center
---

# Zadanie 3.3.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.movies.aggregate([
    {$match: {title: "The Green Mile"}},
    {$lookup: {from: "comments", localField: "_id", foreignField: "movie_id", as: "comments"}}
])
```
</details>

---
layout: center
---

# Zadanie 3.4.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.movies.aggregate([
    { $bucket: { groupBy: "$year", boundaries: [1900, 1950, 1970, 1990, 2000, 2010, 2020], default: "others" } },
    { $merge: { into: "movies_buckets"} }
])
```
</details>

---

# Indeksy

1. Czym jest indeks?
2. Po co go tworzymy.
3. Rodzaje indeksów.
4. Przykład tworzenia.

<br>

```
db.collection.createIndex({field: 1}, {background: true})
```

---
layout: center
---

# Zadanie 4.1.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.movies.getIndices()
```
</details>

---
layout: center
---

# Zadanie 4.2.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.movies.createIndex({title: 1})
```
</details>

---
layout: center
---

# Zadanie 4.3.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.movies.createIndex({type: 1, year: 1}, {background: true})
```
</details>

---
layout: center
---

# Zadanie 4.4.

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```js
db.movies.dropIndex("title_1")
```
</details>

---
layout: fact
hideInToc: true
---

# Koniec zajęć 1