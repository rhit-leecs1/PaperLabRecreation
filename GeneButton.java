import java.awt.*;
import java.util.*;
import javax.swing.*;
public class GeneButton extends JButton{
    private char bin;
    private Color color;
    private int num;
    public GeneButton(int num)
    {
    	super(""+ num);
    	double r = Math.random();
    	if(r<=.5)
    		bin='?';
    	else if(r>.5 && r <= .75)
    		bin='1';
    	else
    		bin='0';
    	
    	if(bin=='0')
    	{
    		color = Color.black;
    		this.setBackground(color);
    		this.setForeground(Color.white);
    	}
    	else if(bin=='1')
    	{
    		color = Color.green;
    		this.setBackground(color);
    		this.setForeground(Color.black);
    	}
    	else
    	{
    		color = Color.ORANGE;
    		this.setBackground(color);
    		this.setForeground(Color.black);
    		
    	}
    	this.setBorderPainted(false);
    	this.setFocusPainted(false);
    	Dimension d = new Dimension(5,5);
    	this.setPreferredSize(d);
    	this.setMaximumSize(d);
    }
    public GeneButton(int num, char bin)
    {
    	super(""+ num);
    	this.bin = bin;
    	if(bin=='0')
    	{
    		color = Color.black;
    		this.setBackground(color);
    		this.setForeground(Color.white);
    	}
    	else if(bin=='1')
    	{
    		color = Color.green;
    		this.setBackground(color);
    		this.setForeground(Color.black);
    	}
    	else
    	{
    		color = Color.ORANGE;
    		this.setBackground(color);
    		this.setForeground(Color.black);
    		
    	}
    	this.setBorderPainted(false);
    	this.setFocusPainted(false);
    	Dimension d = new Dimension(5,5);
    	this.setPreferredSize(d);
    	this.setMaximumSize(d);
    }
	public String toString()
    {
    	return "Gene #" + num + ", " + bin;
    }
    public char getBin()
    {
    	return bin;
    }
    public Color getColor()
    {
    	return color;
    }
    public int getNum()
    {
    	return num;
    }
    public void flipBit()
    {
    	bin = Math.random()<=.5?'0':'1';
    	if(bin=='0')
    	{
    		color = Color.black;
    		this.setBackground(color);
    		this.setForeground(Color.white);
    	}
    	else
    	{
    		color = Color.green;
    		this.setBackground(color);
    		this.setForeground(Color.black);
    	}
    }
	public int countAll(char c) {
		if (bin == c) 
			return 1;
		return 0;
	}
}
