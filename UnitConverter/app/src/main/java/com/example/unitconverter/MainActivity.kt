package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                UnitconverterApp()
                }
            }
        }
    }

@Composable
fun UnitconverterApp() {

    var InputValue by remember { mutableStateOf("") }
    var OutputValue by remember { mutableStateOf("") }
    var InputUnit by remember { mutableStateOf("Meter") }
    var OutputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }

    val conversionMap =
        mapOf("Millimeter" to 0.001, "Centimeter" to 0.01, "Meter" to 1.0, "Kilometer" to 1000.0)

    fun ConvertUnits() {
        val inputvalueDouble = InputValue.toDoubleOrNull() ?: 0.0
        val inputToMeterFactor = conversionMap[InputUnit] ?: 1.0
        val meterToOutputFactor = conversionMap[OutputUnit] ?: 1.0
        val result =
            (inputvalueDouble * inputToMeterFactor / meterToOutputFactor * 100.0).roundToInt() / 100.0
        OutputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //columns
        Text("Unit Converter", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = InputValue,
            onValueChange = { InputValue = it },
            label = { Text("Value here", style = MaterialTheme.typography.bodyMedium) })
        Spacer(modifier = Modifier.height(8.dp))
        Row() {
            //rows
            //Box - 1
            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(InputUnit, style = MaterialTheme.typography.bodyMedium)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop down menu arrow")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text("Millimeter") },
                        onClick = { iExpanded = false; InputUnit = "Millimeter"; ConvertUnits() })
                    DropdownMenuItem(text = { Text("Centimeter") },
                        onClick = { iExpanded = false; InputUnit = "Centimeter"; ConvertUnits() })
                    DropdownMenuItem(text = { Text("Meter") },
                        onClick = { iExpanded = false; InputUnit = "Meter"; ConvertUnits() })
                    DropdownMenuItem(text = { Text("Kilometer") },
                        onClick = { iExpanded = false; InputUnit = "Kilometer"; ConvertUnits() })
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            //Box - 2
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(OutputUnit, style = MaterialTheme.typography.bodyMedium)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop down menu arrow")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text("Millimeter") },
                        onClick = { oExpanded = false; OutputUnit = "Millimeter"; ConvertUnits() })
                    DropdownMenuItem(text = { Text("Centimeter") },
                        onClick = { oExpanded = false; OutputUnit = "Centimeter"; ConvertUnits() })
                    DropdownMenuItem(text = { Text("Meter") },
                        onClick = { oExpanded = false; OutputUnit = "Meter"; ConvertUnits() })
                    DropdownMenuItem(text = { Text("Kilometer") },
                        onClick = { oExpanded = false; OutputUnit = "Kilometer"; ConvertUnits() })
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

        }
        Text("Result -> $OutputValue $OutputUnit", style = MaterialTheme.typography.bodyLarge)

    }

}

@Preview(showBackground = true)
@Composable
fun UnitconverterAppPreview() {
    UnitconverterApp()
}