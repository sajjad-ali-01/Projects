using System;
using System.Windows.Forms;

namespace Calculator
{
    public partial class Calculator : Form
    {
        //calculator Variables
        decimal firstnumber, secondnumber, result;
        int oop; //1=Sum,2=Minus,3=Multiply,4=Divide,5=Reminder
        public Calculator()
        {
            InitializeComponent();
        }

        private void RemoveZero(string number)
        {
            if (informationlabel.Text.Trim() != string.Empty)
            {
                informationlabel.Text = string.Empty;
            }
            if (ResultTextbox.Text == "0")
            {
                ResultTextbox.Text = number;
            }
            else
            {
                ResultTextbox.Text += number;
            }
          
        }

        private void button1_Click(object sender, EventArgs e)
        {
            RemoveZero("1");
        }


        private void button2_Click(object sender, EventArgs e)
        {
            RemoveZero("2");
        }

        private void button3_Click(object sender, EventArgs e)
        {
            RemoveZero("3");
        }

        private void button4_Click(object sender, EventArgs e)
        {
            RemoveZero("4");
        }

        private void button5_Click(object sender, EventArgs e)
        {
            RemoveZero("5");
        }

        private void button6_Click(object sender, EventArgs e)
        {
            RemoveZero("6");
        }

        private void button7_Click(object sender, EventArgs e)
        {
            RemoveZero("7");
        }

        private void button8_Click(object sender, EventArgs e)
        {
            RemoveZero("8");
        }

        private void button9_Click(object sender, EventArgs e)
        {
            RemoveZero("9");
        }

        private void button0_Click(object sender, EventArgs e)
        {
            RemoveZero("0");
        }

        private void plusbutton_Click(object sender, EventArgs e)
        {
            //variable user input 
            if (ResultTextbox.Text.Trim() == string.Empty || ResultTextbox.Text.Trim() == "0")
            { 
            MessageBox.Show("Please enter valid value", "Error", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
                firstnumber = Convert.ToDecimal(ResultTextbox.Text);
            oop = 1;
           // MessageBox.Show(firstnumber.ToString());
            ResultTextbox.Text = " ";
            informationlabel.Text = "Enter second value";
            informationlabel.Visible = true;
        }

        private void minusbutton_Click(object sender, EventArgs e)
        {
            //variable user input 
            if (ResultTextbox.Text.Trim() == string.Empty || ResultTextbox.Text.Trim() == "0")
            {
                MessageBox.Show("Please enter valid value", "Error", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            firstnumber = Convert.ToDecimal(ResultTextbox.Text);
            oop = 2;
            // MessageBox.Show(firstnumber.ToString());
            ResultTextbox.Text = " ";
            informationlabel.Text = "Enter second value";
            informationlabel.Visible = true;
        }

        private void multiplybutton_Click(object sender, EventArgs e)
        {
            //variable user input 
            if (ResultTextbox.Text.Trim() == string.Empty || ResultTextbox.Text.Trim() == "0")
            {
                MessageBox.Show("Please enter valid value", "Error", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            firstnumber = Convert.ToDecimal(ResultTextbox.Text);
            oop = 3;
            // MessageBox.Show(firstnumber.ToString());
            ResultTextbox.Text = " ";
            informationlabel.Text = "Enter second value";
            informationlabel.Visible = true;
        }

        private void dividebutton_Click(object sender, EventArgs e)
        {
            //variable user input 
            if (ResultTextbox.Text.Trim() == string.Empty || ResultTextbox.Text.Trim() == "0")
            {
                MessageBox.Show("Please enter valid value", "Error", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            firstnumber = Convert.ToDecimal(ResultTextbox.Text);
            oop = 4;
            // MessageBox.Show(firstnumber.ToString());
            ResultTextbox.Text = " ";
            informationlabel.Text = "Enter second value";
            informationlabel.Visible = true;
        }

        private void reminderbutton_Click(object sender, EventArgs e)
        {
            //variable user input 
            if (ResultTextbox.Text.Trim() == string.Empty || ResultTextbox.Text.Trim() == "0")
            {
                MessageBox.Show("Please enter valid value", "Error", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            firstnumber = Convert.ToDecimal(ResultTextbox.Text);
            oop = 5;
            // MessageBox.Show(firstnumber.ToString());
            ResultTextbox.Text = " ";
            informationlabel.Text = "Enter second value";
            informationlabel.Visible = true;
        }

        private void clearbutton_Click(object sender, EventArgs e)
        {
            firstnumber = 0.0m;
            secondnumber = 0.0m;
            oop = 0;
            ResultTextbox.Text = "0";
        }

        private void pointbutton_Click(object sender, EventArgs e)
        {
          if(ResultTextbox.Text!="0")
            {
                if(!ResultTextbox.Text.Contains("."))
                {
                    ResultTextbox.Text += ".";
                }
            }
           
        }

        private void button13_Click(object sender, EventArgs e)
        {
            if (ResultTextbox.Text.Trim() == string.Empty || ResultTextbox.Text.Trim() == "0")
            {
                MessageBox.Show("Please enter valid value", "Error", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            secondnumber = Convert.ToDecimal(ResultTextbox.Text);
            Calculateresult(oop);
            
        }
        private void Calculateresult(int oop)
        {
            switch (oop)
            {
                case 1:
                    {
                        result = firstnumber + secondnumber;
                        break;
                    }
                case 2:
                    {
                        result = firstnumber - secondnumber;
                        break;
                    }
                case 3:
                    {
                        result = firstnumber * secondnumber;
                        break;
                    }
                case 4:
                    {
                        result = firstnumber / secondnumber;
                        break;
                    }
                case 5:
                    {
                        result = firstnumber % secondnumber;

                        break;
                    }
                default:
                    {
                        break;
                    }
            }
                    ResultTextbox.Text =decimal.Round (result,3).ToString();
            
        }
    }
}
