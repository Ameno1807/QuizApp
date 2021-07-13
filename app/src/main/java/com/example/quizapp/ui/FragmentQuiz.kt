package com.example.quizapp.ui

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.view.*
import androidx.navigation.fragment.navArgs
import androidx.activity.addCallback
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentQuizBinding
import com.example.quizapp.model.QuizModel


class FragmentQuiz: Fragment() {

    private lateinit var questions: MutableList<QuizModel>
    private var index = 0
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: FragmentQuizArgs by navArgs()
        index = args.answer
        questions = args.questions.toMutableList()
        binding.nextButton.visibility = View.INVISIBLE
        stateRadioButton()
        setNextQuestion()


        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            questions[index].userChoice = when (id) {
                R.id.option_one -> 0
                R.id.option_two -> 1
                R.id.option_three -> 2
                R.id.option_four -> 3
                R.id.option_five -> 4
                else -> -1
            }
            binding.nextButton.visibility = View.VISIBLE
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            index--
            setTheme()
            findNavController().navigate(
                FragmentQuizDirections.actionQuizFragmentSelf(
                    index,
                    questions.toTypedArray()
                )
            )
        }


        binding.toolbar.setNavigationOnClickListener {
            index--
            findNavController().navigate(
                FragmentQuizDirections.actionQuizFragmentSelf(
                    index,
                    questions.toTypedArray()
                )
            )
            setTheme()
        }

        binding.nextButton.setOnClickListener {
            index++
            findNavController().navigate(
                FragmentQuizDirections.actionQuizFragmentSelf(
                    index,
                    questions.toTypedArray()
                )
            )
            setTheme()
            if (index >= 5) {
                findNavController().navigate(
                    FragmentQuizDirections.actionQuizFragmentToResultFragment(
                        questions.toTypedArray()
                    )
                )
            }

        }

        binding.previousButton.setOnClickListener {
            index--
            setTheme()
            findNavController().navigate(
                FragmentQuizDirections.actionQuizFragmentSelf(
                    index,
                    questions.toTypedArray()
                )
            )

        }
    }

    private fun setNextQuestion() {
        binding.question.text = questions[index].question
        binding.optionOne.text = questions[index].solutions?.get(0)
        binding.optionTwo.text = questions[index].solutions?.get(1)
        binding.optionThree.text = questions[index].solutions?.get(2)
        binding.optionFour.text = questions[index].solutions?.get(3)
        binding.optionFive.text = questions[index].solutions?.get(4)
        binding.toolbar.title = "Question ${index+1}"
        binding.questionNumber.text = "${index+1}/5"
        binding.previousButton.isEnabled = index != 0
        setView()

        if (index == 4){
            binding.nextButton.text = "Submit"
        }

        if (index == 0) {
            binding.toolbar.navigationIcon = null
            binding.previousButton.visibility = View.INVISIBLE
        }

        if (questions[index].userChoice != -1){
            binding.nextButton.visibility = View.VISIBLE

        }


    }

    private fun setView(){
        when(index) {
            1 -> {
                binding.viewQues.setBackgroundResource(R.drawable.radio_back_2)
                binding.viewAppbar.setBackgroundResource(R.drawable.bottom_back_2)
                binding.viewNumber.setBackgroundResource(R.drawable.back_r_2)
            }
            2 -> {
                binding.viewQues.setBackgroundResource(R.drawable.radio_back_3)
                binding.viewAppbar.setBackgroundResource(R.drawable.bottom_back_3)
                binding.viewNumber.setBackgroundResource(R.drawable.back_r_3)
            }
            3 -> {
                binding.viewQues.setBackgroundResource(R.drawable.radio_back_4)
                binding.viewAppbar.setBackgroundResource(R.drawable.bottom_back_4)
                binding.viewNumber.setBackgroundResource(R.drawable.back_r_4)
            }
            4 -> {
                binding.viewQues.setBackgroundResource(R.drawable.radio_back_5)
                binding.viewAppbar.setBackgroundResource(R.drawable.bottom_back_5)
                binding.viewNumber.setBackgroundResource(R.drawable.back_r_5)
            }
        }
    }

    private fun setTheme() {
        when (index) {
            0 -> {
                requireContext().setTheme(R.style.Theme_Quiz)
                requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(),
                    R.color.deep_orange_100_dark
                )

            }
            1 ->{
                requireContext().setTheme(R.style.Theme_Quiz_Three)
                requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(),
                    R.color.deep_purple_100_dark
                )


            }
            2 -> {
                requireContext().setTheme(R.style.Theme_Quiz_Fourth)
                requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(),
                    R.color.light_green_100_dark
                )
            }
            3 -> {
                requireContext().setTheme(R.style.Theme_Quiz_Fifth)
                requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(),
                    R.color.yellow_100_dark
                )
            }
            4 -> {
                requireContext().setTheme(R.style.Theme_Quiz_Second)
                requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(),
                    R.color.cyan_100_dark
                )
            }
            else -> {
                requireContext().setTheme(R.style.Theme_Quiz)
                requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(),
                    R.color.deep_orange_100_dark
                )
            }
        }

    }

    private fun stateRadioButton(){
        when(questions[index].userChoice){
            0 -> binding.radioGroup.check(R.id.option_one)
            1 -> binding.radioGroup.check(R.id.option_two)
            2 -> binding.radioGroup.check(R.id.option_three)
            3 -> binding.radioGroup.check(R.id.option_four)
            4 -> binding.radioGroup.check(R.id.option_five)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}