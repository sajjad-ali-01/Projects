using System;
using System.Windows.Forms;

namespace Assignment_4
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            Decimal marks1, marks2, marks3, marks4, marks5, marks6, credithour1, credithour2, credithour3, credithour4, credithour5, credithour6, sum;
            if (s1.Text == "" || s2.Text == "" || s3.Text == "" || s4.Text == "" || s5.Text == "" || s6.Text == "")
            {
                MessageBox.Show("Fill all of the Subjects ");
            }
            else if (CB1.Text == "0" || CB2.Text == "0" || CB3.Text == "0" || CB4.Text == "0" || CB5.Text == "0" || CB6.Text == "0")
            {
                MessageBox.Show("Fill the Credit Hour Box");
            }
            else if (OP1.Text == "" || OP2.Text == "" || OP3.Text == "" || OP4.Text == "" || OP5.Text == "" || OP6.Text == "")
            {
                MessageBox.Show("Fill the Obtain marks");
            }
            else
            {

                marks1 = Convert.ToDecimal(OP1.Text);
                marks2 = Convert.ToDecimal(OP2.Text);
                marks3 = Convert.ToDecimal(OP3.Text);
                marks4 = Convert.ToDecimal(OP4.Text);
                marks5 = Convert.ToDecimal(OP5.Text);
                marks6 = Convert.ToDecimal(OP6.Text);
                credithour1 = Convert.ToDecimal(CB1.Text);
                credithour2 = Convert.ToDecimal(CB2.Text);
                credithour3 = Convert.ToDecimal(CB3.Text);
                credithour4 = Convert.ToDecimal(CB4.Text);
                credithour5 = Convert.ToDecimal(CB5.Text);
                credithour6 = Convert.ToDecimal(CB6.Text);

                //for withdrawl courses
                if (W1.CheckState == CheckState.Checked)
                {
                    marks1 = 0;
                    credithour1 = 0;
                }
                if (W2.CheckState == CheckState.Checked)
                {
                    marks2 = 0;
                    credithour2 = 0;
                }
                if (W3.CheckState == CheckState.Checked)
                {
                    marks3 = 0;
                    credithour3 = 0;
                }
                if (W4.CheckState == CheckState.Checked)
                {
                    marks4 = 0;
                    credithour4 = 0;
                }
                if (W5.CheckState == CheckState.Checked)
                {
                    marks5 = 0;
                    credithour5 = 0;
                }
                if (W6.CheckState == CheckState.Checked)
                {
                    marks6 = 0;
                    credithour6 = 0;
                }
                
                //  for Calculation
                sum = credithour1 + credithour2 + credithour3 + credithour4 + credithour5 + credithour6;
                decimal CGPA = (point(marks1) * credithour1) + (point(marks2) * credithour2) + (point(marks3) * credithour3) + (point(marks4) * credithour4) + (point(marks5) * credithour5) + (point(marks6) * credithour6);
                decimal GGPA = CGPA / sum;
                string grade1 = Result(GGPA);
                RE.Text = grade1;
                TG.Text = Convert.ToString(Math.Round(GGPA, 2, MidpointRounding.ToEven));
                CP.Text = Convert.ToString(sum);
                g1.Text = grade(marks1);
                g2.Text = grade(marks2);
                g3.Text = grade(marks3);
                g4.Text = grade(marks4);
                g5.Text = grade(marks5);
                g6.Text = grade(marks6);
                p1.Text = Convert.ToString(point(marks1));
                p2.Text = Convert.ToString(point(marks2));
                p3.Text = Convert.ToString(point(marks3));
                p4.Text = Convert.ToString(point(marks4));
                p5.Text = Convert.ToString(point(marks5));
                p6.Text = Convert.ToString(point(marks6));
                if (GGPA > 0)
                {
                    RE.Visible = true;
                    TG.Visible = true;
                    CP.Visible = true;
                    g1.Visible = true;
                    g2.Visible = true;
                    g3.Visible = true;
                    g4.Visible = true;
                    g5.Visible = true;
                    g6.Visible = true;
                    p1.Visible = true;
                    p2.Visible = true;
                    p3.Visible = true;
                    p4.Visible = true;
                    p5.Visible = true;
                    p6.Visible = true;
                }



            }
        }

        //for calculating points
        Decimal point(decimal marks)
            {
                Decimal p = 0;
            
                if (marks < 50)
                {
                    p = 0;
                }
                else if (marks >= 50 && marks < 60)
                {
                    p = 1;
                }
                else if (marks >= 60 && marks < 70)
                {
                    p = 2;
                }
                else if (marks >= 70 && marks < 80)
                {
                    p = 3;
                }
                else if (marks >= 80 && marks < 90)
                {
                    p = 4;
                }
                else if (marks >= 90 && marks <= 100)
                {
                    p = 4;
                }
            
                return p;
            
        }
        //for calculating grade
        string grade(decimal marks)
        {
            string g = " ";
            
            if (marks >= 50 && marks < 60)
            {
                g = "D";
            }
            else if (marks >= 60 && marks < 70)
            {
                g = "C";
            }
            else if (marks >= 70 && marks < 80)
            {
                g = "B";
            }
            else if (marks >= 80 && marks < 90)
            {
                g = "A";
            }
            else if (marks>=90&& marks<=100)
            {
                g = "A+";
            }
            else
            {

                if (W1.CheckState == CheckState.Checked)
                {
                    g = "W";

                }
                else if (W2.CheckState == CheckState.Checked)
                {
                    g = "W";
                   
                }
                else if (W3.CheckState == CheckState.Checked)
                {
                    g = "W";

                }
                else if (W4.CheckState == CheckState.Checked)
                {
                    g = "W";

                }
                else if (W5.CheckState == CheckState.Checked)
                {
                    g = "W";

                }
                else if (W6.CheckState == CheckState.Checked)
                {
                    g = "W";
                }
                else 
                {
                    g = "F";
                }
            }

            return g;

        }
        //for calculating Result
        string Result(decimal GGPA)
        {
            string g = " ";
            if (GGPA<1)
            {
                g = "Fail";
            }
            else if (GGPA >= 1&&GGPA <2)
            {
                g = "Pass";
            }
            else if (GGPA >= 2 && GGPA < 3)
            {
                g = "Merit";
            }
            else if (GGPA >= 3 && GGPA < 4)
            {
                g = "Very Good";
            }
            else if (GGPA == 4)
            {
                g = "Execellent";
            }
            return g;

        }

        private void W6_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void Clear_Click(object sender, EventArgs e)
        {
            OP1.Text = "";
            OP2.Text = "";
            OP3.Text = "";
            OP4.Text = "";
            OP5.Text = "";
            OP6.Text = "";

            CB1.Text = "";
            CB2.Text = "";
            CB3.Text = "";
            CB4.Text = "";
            CB5.Text = "";
            CB6.Text = "";

            W1.Checked = false;
            W2.Checked = false;
            W3.Checked = false;
            W4.Checked = false;
            W5.Checked = false;
            W6.Checked = false;

            s1.Text = "";
            s2.Text = "";
            s3.Text = "";
            s4.Text = "";
            s5.Text = "";
            s6.Text = "";

            TG.Text = "" ;
            RE.Text = "" ;
            CP.Text = "" ;

            g1.Text = "";
            g2.Text = "";
            g3.Text = "";
            g4.Text = "";
            g5.Text = "";
            g6.Text = "";

            p1.Text = "";
            p2.Text = "";
            p3.Text = "";
            p4.Text = "";
            p5.Text = "";
            p6.Text = "";

        }
    }
}
