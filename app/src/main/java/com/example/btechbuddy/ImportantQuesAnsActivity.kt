package com.example.btechbuddy

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.btechbuddy.Semester1Activity.EngineeringPhysicsActivity
import com.example.btechbuddy.Semester1Activity.EnvironmentAndEcologyActivity
import com.example.btechbuddy.Semester1Activity.FundamentalElectricalEngineeringActivity
import com.example.btechbuddy.Semester1Activity.ProgrammingProblemSolvingActivity
import com.example.btechbuddy.Semester2Activity.ChemistryActivity
import com.example.btechbuddy.Semester2Activity.FundamentalElectronicsActivity
import com.example.btechbuddy.Semester2Activity.FundamentalMechanicalActivity
import com.example.btechbuddy.Semester2Activity.SoftSkillActivity
import com.example.btechbuddy.Semester3Activity.ComputerOrganizationArchitectureActivity
import com.example.btechbuddy.Semester3Activity.DataStructureActivity
import com.example.btechbuddy.Semester3Activity.DiscreteStructureActivity
import com.example.btechbuddy.Semester3Activity.HumanValueActivity
import com.example.btechbuddy.Semester3Activity.PythonProgrammingActivity
import com.example.btechbuddy.Semester4Activity.CyberSecurityActivity
import com.example.btechbuddy.Semester4Activity.OopsActivity
import com.example.btechbuddy.Semester4Activity.OperatingSystemActivity
import com.example.btechbuddy.Semester4Activity.TechnicalCommunicationActivity
import com.example.btechbuddy.Semester4Activity.TheoryOfAutomataActivity
import com.example.btechbuddy.databinding.ActivityImportantQuesAnsBinding
import com.example.btechbuddy.databinding.ActivityMainBinding

class ImportantQuesAnsActivity : AppCompatActivity(){
    private val binding: ActivityImportantQuesAnsBinding by lazy{
        ActivityImportantQuesAnsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.title = "Subjects"

//        1st Semester

        binding.cardViewEnvironment.setOnClickListener{
            val intent = Intent(this, EnvironmentAndEcologyActivity::class.java)
            startActivity(intent)
        }

        binding.cardViewProgrammingProblem.setOnClickListener{
            val intent = Intent(this, ProgrammingProblemSolvingActivity::class.java)
            startActivity(intent)
        }

        binding.cardViewFundamentalElectrical.setOnClickListener{
            val intent = Intent(this, FundamentalElectricalEngineeringActivity::class.java)
            startActivity(intent)
        }

        binding.cardViewEngineeringPhysics.setOnClickListener{
            val intent = Intent(this, EngineeringPhysicsActivity::class.java)
            startActivity(intent)
        }

//        2nd Semester ******************

        binding.cardViewFundamentalElectronics.setOnClickListener{
            val intent = Intent(this, FundamentalElectronicsActivity::class.java)
            startActivity(intent)
        }

        binding.engineeringChemistry.setOnClickListener{
            val intent = Intent(this, ChemistryActivity::class.java)
            startActivity(intent)
        }

        binding.cardViewFundamentalMechanical.setOnClickListener{
            val intent = Intent(this, FundamentalMechanicalActivity::class.java)
            startActivity(intent)
        }
        binding.cardViewSoftSkills.setOnClickListener{
            val intent = Intent(this, SoftSkillActivity::class.java)
            startActivity(intent)
        }
//        3rd Semester
        binding.cardViewDataStructure.setOnClickListener{
            val intent = Intent(this, DataStructureActivity::class.java)
            startActivity(intent)
        }
        binding.cardViewComputerOrganizationArchitecture.setOnClickListener{
            val intent = Intent(this, ComputerOrganizationArchitectureActivity::class.java)
            startActivity(intent)
        }
        binding.cardViewDiscreteStructure.setOnClickListener{
            val intent = Intent(this, DiscreteStructureActivity::class.java)
            startActivity(intent)
        }
        binding.cardViewPythonProgramming.setOnClickListener{
            val intent = Intent(this, PythonProgrammingActivity::class.java)
            startActivity(intent)
        }
        binding.cardViewUhv.setOnClickListener{
            val intent = Intent(this, HumanValueActivity::class.java)
            startActivity(intent)
        }

//        4th Semester **********************
        binding.cardViewOperatingSystem.setOnClickListener{
            val intent = Intent(this, OperatingSystemActivity::class.java)
            startActivity(intent)
        }

        binding.cardViewTheoryAutomata.setOnClickListener{
            val intent = Intent(this, TheoryOfAutomataActivity::class.java)
            startActivity(intent)
        }
        binding.cardViewOops.setOnClickListener{
            val intent = Intent(this, OopsActivity::class.java)
            startActivity(intent)
        }
        binding.cardViewCyberSecurity.setOnClickListener{
            val intent = Intent(this, CyberSecurityActivity::class.java)
            startActivity(intent)
        }
        binding.cardViewTechnicalCommunication.setOnClickListener{
            val intent = Intent(this, TechnicalCommunicationActivity::class.java)
            startActivity(intent)
        }

    }
}