# Zajęcia 1 (Operowanie bezpośrednio na bazie)

<br>
Na tych zajęciach skupimy się na operowaniu bezpośrednio na bazie MongoDB.

Nauczysz się wykonywać zapytania CRUD, wyszukiwać za pomocą bardziej skomplikowanych warunków, używać agregacji oraz tworzyć indeksy.

<Toc columns="3" minDepth="2" maxDepth="3" />

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