package com.example.quizapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentResultQuizBinding
import com.example.quizapp.model.QuizModel
import kotlin.system.exitProcess


class FragmentResultQuiz: Fragment() {
    private var _binding: FragmentResultQuizBinding? = null
    private val binding get() = _binding!!
    private lateinit var questions: MutableList<QuizModel>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args : FragmentResultQuizArgs by navArgs()
        questions = args.questions.toMutableList()
        binding.score.text = "${answers()} / 5 "
        swapEmotion()

        binding.restart.setOnClickListener {
           findNavController().navigate(FragmentResultQuizDirections.actionResultFragmentToMainFragment())
           for (i in 0..4){
               questions[i].userChoice = -1
           }
        }
        binding.exit.setOnClickListener {
            exitProcess(-1)
        }

        binding.share.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT,"Results")
                .putExtra(Intent.EXTRA_TEXT, share())
            startActivity(intent)
        }
    }

    private fun share(): String {
        var count = 1
        val stringBuilder = StringBuilder("")
        return stringBuilder.apply {
            append("Результат: ${answers()} из 5 \n\n")
            for (question in questions) {
                append(
                    "${count++}) ${question.question}\n" +
                            "Ваш Ответ: ${question.solutions?.get(question.userChoice)}\n" +
                            "Правильный ответ: ${question.answer}\n\n"

                )
            }
        }.toString()
    }

    private fun answers(): Int {
        return questions.filter { it.answer == it.solutions?.get(it.userChoice) }.size
    }

    private fun swapEmotion() {
        if (answers() <= 3){
            binding.imageView.setImageResource(R.drawable.upset)
            binding.textView.text = "Don't worry, try again!"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}