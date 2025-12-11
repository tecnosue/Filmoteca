package com.campusdigitalfp.filmoteca.sampledata
import androidx.compose.runtime.mutableStateListOf

import com.campusdigitalfp.filmoteca.R

object FilmDataSource {
    //val films: MutableList<Film> = mutableListOf()
    val films = mutableStateListOf<Film>() //la UI se actualiza automáticamente cuando añades películas
    init {
        val f1 = Film(
            id = 0,
            title = "Harry Potter y la piedra filosofal",
            director = "Chris Columbus",
            imageResId = R.drawable.harry_potter_piedra,
            comments = "Una aventura mágica en Hogwarts.",
            format = Film.FORMAT_DVD,
            genre = Film.GENRE_ACTION,
            imdbUrl = "http://www.imdb.com/title/tt0241527",
            year = 2001
        )
        films.add(f1)

        // Segunda película: Regreso al futuro
        val f2 = Film(
            id = 1,
            title = "Regreso al futuro",
            director = "Robert Zemeckis",
            imageResId = R.drawable.regreso_al_futuro,
            comments = "",
            format = Film.FORMAT_DIGITAL,
            genre = Film.GENRE_SCIFI,
            imdbUrl = "http://www.imdb.com/title/tt0088763",
            year = 1985
        )
        films.add(f2)

        // Tercera película: El rey león
        val f3 = Film(
            id = 2,
            title = "El rey león",
            director = "Roger Allers, Rob Minkoff",
            imageResId = R.drawable.el_rey_leon,
            comments = "Una historia de crecimiento y responsabilidad.",
            format = Film.FORMAT_BLURAY,
            genre = Film.GENRE_ACTION,
            imdbUrl = "http://www.imdb.com/title/tt0110357",
            year = 1994
        )
        films.add(f3)
    }
}
