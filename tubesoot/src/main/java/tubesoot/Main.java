/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubesoot;

/**
 *
 * @author john
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.HashMap;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import javax.swing.border.Border;
import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.AttributeKeys;

import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.DiamondFigure;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.EllipseFigure;
import org.jhotdraw.draw.ImageFigure;
import org.jhotdraw.draw.LabeledLineConnectionFigure;
import org.jhotdraw.draw.LineConnectionFigure;
import org.jhotdraw.draw.LineFigure;
import org.jhotdraw.draw.ListFigure;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.draw.RectangleFigure;

import org.jhotdraw.draw.RoundRectangleFigure;
import org.jhotdraw.draw.TextAreaFigure;
import org.jhotdraw.draw.TextFigure;
import org.jhotdraw.draw.TriangleFigure;
import org.jhotdraw.draw.action.ButtonFactory;
import org.jhotdraw.draw.tool.CreationTool;
import org.jhotdraw.samples.mini.BorderRectangleFigure;
import org.jhotdraw.samples.mini.StraightLineFigure;
import org.jhotdraw.samples.net.figures.NodeFigure;
import org.jhotdraw.samples.pert.figures.SeparatorLineFigure;
import org.jhotdraw.util.ResourceBundleUtil;

public class Main extends JApplet {

    @Override
    public void init() {

        // Initialize JHotDraw resources
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");

        // Create a drawing view with a default drawing, and
        // input/output formats for basic clipboard support.
        final DrawingView view = new DefaultDrawingView();
        final Drawing drawing = new QuadTreeDrawing();
        drawing.addInputFormat(new org.jhotdraw.draw.io.SerializationInputOutputFormat());
        drawing.addOutputFormat(new org.jhotdraw.draw.io.SerializationInputOutputFormat());
        drawing.set(AttributeKeys.CANVAS_WIDTH, 2000d);
        drawing.set(AttributeKeys.CANVAS_HEIGHT, 1800d);
        view.setDrawing(drawing);

        // Wrap the drawing view in scroll pane
        JScrollPane sp = new JScrollPane(view.getComponent(),
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Create a common drawing editor for the views
        DrawingEditor editor = new DefaultDrawingEditor();
        editor.add(view);

        // Create a tool bar
        JToolBar tb = new JToolBar();
        tb.setOrientation(JToolBar.VERTICAL);
        tb.setFloatable(false);
        tb.addSeparator();

        // Add a selection tool to the toolbar
        ButtonFactory.addSelectionToolTo(tb, editor);

        // Add creation tool for transparent green rectangles
        HashMap<AttributeKey, Object> a = new HashMap<AttributeKey, Object>();
        AttributeKeys.FILL_COLOR.put(a, new Color(0, 255, 0, 80));
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new RectangleFigure(), a), "edit.createRectangle", labels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new DiamondFigure(), a), "edit.createDiamond", labels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new EllipseFigure(), a), "edit.createEllipse", labels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new TriangleFigure(), a), "edit.createTriangle", labels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new StraightLineFigure(), a), "edit.createLine", labels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new RoundRectangleFigure(), a), "edit.createRoundRectangle", labels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new LineConnectionFigure(), a), "edit.createLineConnection", labels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new TextFigure(), a), "edit.createText", labels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new TextAreaFigure(), a), "edit.createTextArea", labels);

        // Add transformation button
        JButton button = new JButton();
        button.setText("0.5");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent aE) {
                AffineTransform trans = AffineTransform.getScaleInstance(0.5, 0.5);
                drawing.transform(trans);
                view.setScaleFactor(1);
            }
        });
        tb.add(button);

        // Let edit here



        // finish edit

        // Set up the content pane
        setSize(600, 500);
        setLayout(new java.awt.BorderLayout());
        getContentPane().add(sp, BorderLayout.CENTER);
        getContentPane().add(tb, BorderLayout.WEST);
    }
}
