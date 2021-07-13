package com.example.quizapp.model

import android.os.Parcel
import android.os.Parcelable

data class QuizModel(
    val question: String?,
    val solutions: ArrayList<String>?,
    var answer: String?,
    var userChoice: Int = -1
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("solutions"),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(question)
        parcel.writeString(answer)
        parcel.writeInt(userChoice)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuizModel> {
        override fun createFromParcel(parcel: Parcel): QuizModel {
            return QuizModel(parcel)
        }

        override fun newArray(size: Int): Array<QuizModel?> {
            return arrayOfNulls(size)
        }
    }
}


object QuizGenerator {
    val questions = mutableListOf(
        QuizModel(
            question = "В каком году был выпущен первый фильм «Железный человек»?",
            solutions = arrayListOf(
                "2005",
                "2008",
                "2010",
                "2012",
                "2003"
            ),
            answer = "2008"

        ),
        QuizModel(
            question = "Как называется молот Тора?",
            solutions = arrayListOf(
                "Mjolnir",
                "Ваны",
                "Сонм богов",
                "Ведьма",
                "Камень",
            ),
            answer = "Mjolnir"

        ),
        QuizModel(
           question = "Из чего сделан щит Капитана Америки?",
            solutions = arrayListOf(
                "Adamantium",
                "Вибраниум",
                "Прометий",
                "Карбонадий",
                "Кремнийй",
            ) ,
            answer = "Вибраниум"

        ),
        QuizModel(
            question =  "Прежде чем стать Виженом, как звали дворецкого из Железного Человека?",
            solutions = arrayListOf(
                "ГОМЕР",
                "JARVIS",
                "АЛЬФРЕД",
                "MARVIN",
                "ЛЕО",
            ),
            answer = "JARVIS"


        ),
        QuizModel(
            question =   "Каким доктором был Стивен Стрендж?",
            solutions = arrayListOf(
                "Нейрохирург",
                "Кардиоторакальный хирург",
                "Травматолог",
                "Пластический хирург",
                "Невролог",
            ),
            answer = "Нейрохирург"

        ),
    )
}