### 1 - Shorten URL
![Short URL](http://i.imgur.com/MFB7VP4.jpg)

Exemplos:

* Chamada sem CUSTOM_ALIAS
```
PUT http://shortener/create?url=http://www.acme.com

{
   "alias": "XYhakR",
   "url": "http://shortener/u/XYhakR",
   "statistics": {
       "time_taken": "10ms",
   }
}
```

* Chamada com CUSTOM_ALIAS
```
PUT http://shortener/create?url=http://www.acme.com&CUSTOM_ALIAS=acme

{
   "alias": "acme",
   "url": "http://shortener/u/acme",
   "statistics": {
       "time_taken": "12ms",
   }
}
```

### 2 - Retrieve URL
![Retrieve URL](http://i.imgur.com/f9HESb7.jpg)
