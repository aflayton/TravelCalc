import javax.swing.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.event.*;
 
public class TravelCalc extends JFrame implements ActionListener, DocumentListener, ItemListener
{
  Cities c = new Cities();
  String[] cities = c.readCities();
  JComboBox<String> dropDown = new JComboBox<String>(cities);
  JTextField distanceText = new JTextField(6);
  JFrame frame = new JFrame("TravelCalc");
  JPanel dropDownPanel = new JPanel();
  JLabel cityLabel = new JLabel("City: ");
  JLabel distanceLabel = new JLabel("Distance from PIT: ");
  String[] distances = c.readDistances();
  JLabel lblBudget = new JLabel("Budget: ");
  JTextField budget = new JTextField(6);
  JLabel lblDollarsPerMile = new JLabel("Dollars per Mile: ");
  JTextField txtDollarsPerMile = new JTextField(6);
  ReadXMLFile xml = new ReadXMLFile("attractions.xml");
  JCheckBox[] attractBoxes = new JCheckBox[xml.attractions.size()];
  JPanel attractPanel = new JPanel();
  
  public TravelCalc(String s)
  {
    super(s);
    frame.setSize(800,100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(dropDownPanel);
    
    dropDownPanel.add(cityLabel);
    dropDownPanel.add(dropDown);
    dropDownPanel.add(distanceLabel);
    dropDownPanel.add(distanceText);
    dropDownPanel.add(lblBudget);
    dropDownPanel.add(budget);
    dropDownPanel.add(lblDollarsPerMile);
    dropDownPanel.add(txtDollarsPerMile);
    
    dropDownPanel.add(attractPanel);
    
    for(int i = 0; i < xml.attractions.size(); i++)
    {
      attractBoxes[i] = new JCheckBox(xml.attractions.get(i).name);
      attractBoxes[i].addItemListener(this);
    }
    
    distanceText.setEditable(false);
    txtDollarsPerMile.setEditable(false);
        
    dropDown.addActionListener(this);
    budget.getDocument().addDocumentListener(this);
    
    distanceText.setText(distances[0]);
    
		frame.setResizable(false);
    frame.setVisible(true);
  }
    
    public void actionPerformed(ActionEvent ae) 
    {
       
      if (dropDown.getSelectedItem().equals(cities[dropDown.getSelectedIndex()]))
      {
        int x = dropDown.getSelectedIndex();
        distanceText.setText(distances[x]);
        attractPanel.removeAll();
	for(int i = 0; i < xml.attractions.size(); i++)
        {
          if (dropDown.getSelectedItem().equals(xml.attractions.get(i).city))
          {
            attractPanel.add(attractBoxes[i]);
          }
        }
	attractPanel.revalidate();
      }
    }
    public void calculate()
    {
      try
      {
        double budgetNum = Double.parseDouble(budget.getText());
        int distanceNum = Integer.parseInt(distanceText.getText());
        double dpm = (double)Math.round((budgetNum/distanceNum)*100)/100;
        String dpmText = String.valueOf(dpm);
        txtDollarsPerMile.setText(dpmText);
      }
      catch(Exception e)
      {
      }
    }
    public void insertUpdate(DocumentEvent e) 
    {
      calculate();
    }
    public void removeUpdate(DocumentEvent e) 
    {
      calculate();
    }
    public void changedUpdate(DocumentEvent e) 
    {
    }
		
    public void itemStateChanged(ItemEvent e) 
    {
	Object source = e.getItemSelectable();
	double attractPrice=0.0;
	try
	{
		double budgetNum = Double.parseDouble(budget.getText());
		for (int i = 0; i < xml.attractions.size(); i++)
		{
			if (source == attractBoxes[i])
			{
				attractPrice = Double.parseDouble(xml.attractions.get(i).price);
			}
		}
		if (e.getStateChange() == ItemEvent.SELECTED)
		{
			budgetNum -= attractPrice;
		}
		if (e.getStateChange() == ItemEvent.DESELECTED)
		{
			budgetNum += attractPrice;
		}
		budget.setText(String.valueOf(budgetNum));
	}
	catch(Exception ex)
	{
	}
    }
		
    public static void main(String[] args) 
    {
        TravelCalc tc = new TravelCalc("TravelCalc");
    }
}
