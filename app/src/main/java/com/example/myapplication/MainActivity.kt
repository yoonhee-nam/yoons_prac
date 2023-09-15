package com.example.myapplication
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityMainBinding
import com.skydoves.powerspinner.IconSpinnerAdapter
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var items = mutableListOf<DustItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Spinner에서 선택한 값을 받아서 넣어서 아래에 hashMap에 sido로 들어감
        binding.spinnerViewSido.setOnSpinnerItemSelectedListener<String> { _, _, _, text ->
            communicateNetwork(setUpDustParameter(text))
        }
        binding.spinnerViewGoo.setOnSpinnerItemSelectedListener<String> { _, _, _, text ->
            Log.d("miseya", "selectedItem: spinnerViewGoo selected > $text")
            //구 item이 들어오면  stationName == text item을 filtering 해줌 그안에 sidoName,pm10Value 을 가져옴
            var selectedItem = items.filter { f -> f.stationName == text }
            Log.d("miseya", "selecteditem: sidoName >" + selectedItem[0].sidoName)
            Log.d("miseya", "seletedItem: pm10Value >" + selectedItem[0].pm10Value)

            //실시간 데이터이기 때문에 여러개의 리스트중 0번째 데이터로 적용
            binding.tvCityname.text = selectedItem[0].sidoName + " " + selectedItem[0].stationName
            binding.tvDate.text = selectedItem[0].dataTime
            binding.tvP10value.text = selectedItem[0].pm10Value + " ㎍/ "


            //getGrade로 점수를 지정해 준 뒤에 selectedItem[0].pm10Value를 점수대로 나눈 뒤 이미지 변경
            when (getGrade(selectedItem[0].pm10Value)) {
                1 -> {
                    binding.mainBg.setBackgroundColor(Color.parseColor("#9ED2EC"))
                    binding.ivFace.setImageResource(R.drawable.mise1)
                    binding.tvP10grade.text = "좋음"
                }

                2 -> {
                    binding.mainBg.setBackgroundColor(Color.parseColor("#D6A478"))
                    binding.ivFace.setImageResource(R.drawable.mise2)
                    binding.tvP10grade.text = "보통"
                }

                3 -> {
                    binding.mainBg.setBackgroundColor(Color.parseColor("#DF7766"))
                    binding.ivFace.setImageResource(R.drawable.mise4)
                    binding.tvP10grade.text = "나쁨"
                }

                4 -> {
                    binding.mainBg.setBackgroundColor(Color.parseColor("#BB3320"))
                    binding.ivFace.setImageResource(R.drawable.mise5)
                    binding.tvP10grade.text = "매우나쁨"
                }
            }
        }

    }

    //Network관련은 메인쓰레드에선 실행X 메인에서 연결하다가 멈춰있으면 안되니 별도의 쓰레드에 코루틴으로 돌음
    private fun communicateNetwork(param: HashMap<String, String>) = lifecycleScope.launch() {

        //  NetWorkinterface에 있던 getDust호출 getDust가 실행되면 NetWorkClient(retrofit)이 쭉 실행되고 reponseData가 DTO클래스 상태로 들어옴
        val responseData = NetWorkClient.dustNetwork.getDust(param)

        Log.d("Parasing Dust ::", responseData.toString())

        // dustItem위치를 불러와 items에 담아준다.
        items = responseData.response.dustBody.dustItem!!

//        val adapter = IconSpinnerAdapter(binding.spinnerViewGoo)
//        items = responseData.response.dustBody.dustItem!!

        // items에서 stationName데이터만 꺼내서 아래 binding.spinnerViewGoo.setItems(goo)로 넣어준다.
        val goo = ArrayList<String>()
        items.forEach {
            Log.d("add Item:", it.stationName)
            goo.add(it.stationName)
        }
        //현재 위치가 별도 쓰레드 코루틴이고, 별도쓰레드에선 메인쓰레드를 건드릴 수 없어서 runOnUiThread로 지정해준다.
        runOnUiThread {
            binding.spinnerViewGoo.setItems(goo)
        }
    }

    private fun setUpDustParameter(sido: String): HashMap<String, String> {
        val authKey =
            "a066FbkS4RF4q0775BFW12futEVP9BFHfk+kDSijr1vRs99i+uT2Td1/jft3rf2FVN+3KXDOL4mwKNizAu4EsA=="

        return hashMapOf(
            //요청변수(Request Prameter)에 있는 값을 정확히 적어줘야함

            "serviceKey" to authKey,
            "returnType" to "json",
            "numOfRows" to "100",
            //page수나 숫자가 많아지면 위  binding.spinnerViewSido.setOnSpinnerItemSelectedListener<String> { _, _, _, text ->
            //            communicateNetwork(setUpDustParameter(text))
            //        } 부분에 파라미터가 추가됨
            "pageNo" to "1",
            "sidoName" to sido,
            "ver" to "1.0"
        )
    }

    fun getGrade(value: String): Int {
        val mValue = value.toInt()
        var grade = 1
        grade = if (mValue >= 0 && mValue <= 30) {
            1
        } else if (mValue >= 31 && mValue <= 80) {
            2
        } else if (mValue >= 81 && mValue <= 100) {
            3
        } else 4
        return grade
    }
}