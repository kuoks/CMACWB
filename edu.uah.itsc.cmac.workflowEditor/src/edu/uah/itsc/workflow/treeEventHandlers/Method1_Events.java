package edu.uah.itsc.workflow.treeEventHandlers;
//package edu.uah.itsc.workflow.treeEventHandlers;
//
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.graphics.Point;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.Listener;
//
//import edu.uah.itsc.workflow.actionHandler.InputHandler;
//import edu.uah.itsc.workflow.actionHandler.Method1_TreeHandler;
//import edu.uah.itsc.workflow.actionHandler.Method2_TreeHandler;
//import edu.uah.itsc.workflow.actionHandler.OutputHandler;
//import edu.uah.itsc.workflow.movementTrackers.InputCompositeTracker;
//import edu.uah.itsc.workflow.movementTrackers.MethodCompositeTracker;
//import edu.uah.itsc.workflow.movementTrackers.OutputCompositeTracker;
//import edu.uah.itsc.workflow.variableHolder.VariablePoJo;
//import edu.uah.itsc.workflow.wrapperClasses.CompositeWrapper;
//
///**
// * This class handles any events i,.e clicks or double clicks on method1
// * 
// * @author Rohith Samudrala
// * 
// */
//public class Method1_Events {
//
//	Point p;
//
//	// Constructor using point p
//	public Method1_Events(Point p) {
//		super();
//		this.p = p;
//	}
//
//	public void handleMethod1_Events(Object obj) {
//
//		if ( (obj.toString().equals("Method1")) || (obj.toString()
//				.equals("[Method1]"))) {
//
//			// Get an object of the method1 handler class
//			Method1_TreeHandler methodHandlerObj = new Method1_TreeHandler();
//
//			// Set all the required data
//			methodHandlerObj.setChildComposite_WorkSpace(VariablePoJo
//					.getInstance().getChildCreatorObject()
//					.getChildComposite_WorkSpace());
//			methodHandlerObj.setCompositeList(VariablePoJo.getInstance()
//					.getCompositeList());
////			methodHandlerObj.setConnectorList(VariablePoJo.getInstance()
////					.getConnectorList());
//			methodHandlerObj.setConnectorList(VariablePoJo.getInstance()
//					.getConnectorList());
//			methodHandlerObj.setParentComposite(VariablePoJo.getInstance()
//					.getParentComposite());
//			methodHandlerObj.setP(p);
//
//			// Call the method to create the composite
//			methodHandlerObj.methodTreeCompositeCreator();
//
//			// Get the composite created by the method composite creator
//			final CompositeWrapper methodComposite = methodHandlerObj
//					.getMethodComposite();
//
//			// adding mouse listener to the method composite to enable the
//			// it to move
//			methodComposite.addListener(SWT.MouseDown, new Listener() {
//				public void handleEvent(Event e) {
//
//					// Add tracker to the composite
//					MethodCompositeTracker methodTrackerObject = new MethodCompositeTracker();
//					methodTrackerObject.setCompositeList(VariablePoJo
//							.getInstance().getCompositeList());
//					methodTrackerObject.setMethodComposite(methodComposite);
//					methodTrackerObject
//							.setChildComposite_WorkSpace(VariablePoJo
//									.getInstance().getChildCreatorObject()
//									.getChildComposite_WorkSpace());
//					methodTrackerObject.setParentComposite(VariablePoJo
//							.getInstance().getParentComposite());
////					methodTrackerObject.setConnectorList(VariablePoJo
////							.getInstance().getConnectorList());
//					methodTrackerObject.setConnectorList(VariablePoJo
//							.getInstance().getConnectorList());
//					methodTrackerObject.methodTracker();
//				}
//			});
//
//		}
//		
//		else if (obj.toString().equals("Method2") || obj.toString().equals("[Method2]")){
//			// Get an object of the method handler class
//						Method2_TreeHandler methodHandlerObj = new Method2_TreeHandler();
//
//						methodHandlerObj.setChildComposite_WorkSpace(VariablePoJo
//								.getInstance().getChildCreatorObject()
//								.getChildComposite_WorkSpace());
//						methodHandlerObj.setCompositeList(VariablePoJo.getInstance()
//								.getCompositeList());
////						methodHandlerObj.setConnectorList(VariablePoJo.getInstance()
////								.getConnectorList());
//						methodHandlerObj.setConnectorList(VariablePoJo.getInstance()
//								.getConnectorList());
//						// methodHandlerObj.setConnectorObj(MultiPageEditor.getConnectorObj());
//						methodHandlerObj.setParentComposite(VariablePoJo.getInstance()
//								.getParentComposite());
//						// methodHandlerObj.setEndingComposite(mpeObj.getEndingComposite());
//
//						methodHandlerObj.methodTreeCompositeCreator();
//
//						// Get the composite created by the method composite creator
//						final CompositeWrapper methodComposite = methodHandlerObj
//								.getMethodComposite();
//
//						// adding mouse listener to the method composite to enable the
//						// it to move
//						methodComposite.addListener(SWT.MouseDown, new Listener() {
//							public void handleEvent(Event e) {
//
//								MethodCompositeTracker methodTrackerObject = new MethodCompositeTracker();
//								methodTrackerObject.setCompositeList(VariablePoJo
//										.getInstance().getCompositeList());
//								methodTrackerObject.setMethodComposite(methodComposite);
//								methodTrackerObject
//										.setChildComposite_WorkSpace(VariablePoJo
//												.getInstance().getChildCreatorObject()
//												.getChildComposite_WorkSpace());
//								methodTrackerObject.setParentComposite(VariablePoJo
//										.getInstance().getParentComposite());
////								methodTrackerObject.setConnectorList(VariablePoJo
////										.getInstance().getConnectorList());
//								methodTrackerObject.setConnectorList(VariablePoJo
//										.getInstance().getConnectorList());
//								methodTrackerObject.methodTracker();
//							}
//						});
//		}
//
//		else if (obj.toString().equals("Input")) {
//			inputEvent();
//		}
//		
//		else if (obj.toString().equals("Output")) {
//			outputEvent();
//		}
//		else if (obj.toString().equals("Input 1") || (obj.toString()
//				.equals("[Input 1]"))) {
//			System.out.println("in root -> parent1 -> leaf1 is am working");
//			inputEvent();
////			// Set the required data
////			final InputHandler inputHandlerObj = new InputHandler();
////			inputHandlerObj.setChildComposite_WorkSpace(VariablePoJo
////					.getInstance().getChildCreatorObject()
////					.getChildComposite_WorkSpace());
////			inputHandlerObj.setCompositeList(VariablePoJo.getInstance()
////					.getCompositeList());
//////			inputHandlerObj.setConnectorList(VariablePoJo.getInstance()
//////					.getConnectorList());
////			inputHandlerObj.setConnectorList(ConnectorPoJo.getInstance()
////					.getConnectorList());
////
////			// Create the input composite
////			inputHandlerObj.inputCompositeCreator();
////
////			inputHandlerObj.getInputComposite().addListener(SWT.MouseDown,
////					new Listener() {
////						public void handleEvent(Event e) {
////							InputCompositeTracker inputTrackerObject = new InputCompositeTracker();
////							inputTrackerObject.setCompositeList(VariablePoJo
////									.getInstance().getCompositeList());
////							inputTrackerObject
////									.setInputComposite(inputHandlerObj
////											.getInputComposite());
////							inputTrackerObject
////									.setChildComposite_WorkSpace(VariablePoJo
////											.getInstance()
////											.getChildCreatorObject()
////											.getChildComposite_WorkSpace());
////							inputTrackerObject
////									.setParentComposite((CompositeWrapper) VariablePoJo
////											.getInstance()
////											.getChildCreatorObject()
////											.getChildComposite_WorkSpace()
////											.getParent());
//////							inputTrackerObject.setConnectorList(VariablePoJo
//////									.getInstance().getConnectorList());
////							inputTrackerObject.setConnectorList(ConnectorPoJo
////									.getInstance().getConnectorList());
////							inputTrackerObject.inputTracker();
////
////						}
////					});
////
//		} else if (obj.toString().equals("Input 2") || (obj.toString()
//				.equals("[Input 2]"))) {
//			System.out.println("in root -> parent1 -> leaf2 i am working");
//
//			inputEvent();
////			// Set the required data
////			final InputHandler inputHandlerObj = new InputHandler();
////			inputHandlerObj.setChildComposite_WorkSpace(VariablePoJo
////					.getInstance().getChildCreatorObject()
////					.getChildComposite_WorkSpace());
////			inputHandlerObj.setCompositeList(VariablePoJo.getInstance()
////					.getCompositeList());
//////			inputHandlerObj.setConnectorList(VariablePoJo.getInstance()
//////					.getConnectorList());
////			inputHandlerObj.setConnectorList(ConnectorPoJo.getInstance()
////					.getConnectorList());
////
////			// Create the input composite
////			inputHandlerObj.inputCompositeCreator();
////
////			inputHandlerObj.getInputComposite().addListener(SWT.MouseDown,
////					new Listener() {
////						public void handleEvent(Event e) {
////							InputCompositeTracker inputTrackerObject = new InputCompositeTracker();
////							inputTrackerObject.setCompositeList(VariablePoJo
////									.getInstance().getCompositeList());
////							inputTrackerObject
////									.setInputComposite(inputHandlerObj
////											.getInputComposite());
////							inputTrackerObject
////									.setChildComposite_WorkSpace(VariablePoJo
////											.getInstance()
////											.getChildCreatorObject()
////											.getChildComposite_WorkSpace());
////							inputTrackerObject
////									.setParentComposite((CompositeWrapper) VariablePoJo
////											.getInstance()
////											.getChildCreatorObject()
////											.getChildComposite_WorkSpace()
////											.getParent());
//////							inputTrackerObject.setConnectorList(VariablePoJo
//////									.getInstance().getConnectorList());
////							inputTrackerObject.setConnectorList(ConnectorPoJo
////									.getInstance().getConnectorList());
////							inputTrackerObject.inputTracker();
////
////						}
////					});
//
//		} else if (obj.toString().equals("Input 3") || (obj.toString()
//				.equals("[Input 3]"))) {
//			System.out.println("in root -> parent1 -> leaf3 i am working");
//
//			inputEvent();
////			// Set the required data
////			final InputHandler inputHandlerObj = new InputHandler();
////			inputHandlerObj.setChildComposite_WorkSpace(VariablePoJo
////					.getInstance().getChildCreatorObject()
////					.getChildComposite_WorkSpace());
////			inputHandlerObj.setCompositeList(VariablePoJo.getInstance()
////					.getCompositeList());
//////			inputHandlerObj.setConnectorList(VariablePoJo.getInstance()
//////					.getConnectorList());
////			inputHandlerObj.setConnectorList(ConnectorPoJo.getInstance()
////					.getConnectorList());
////
////			// Create the input composite
////			inputHandlerObj.inputCompositeCreator();
////
////			inputHandlerObj.getInputComposite().addListener(SWT.MouseDown,
////					new Listener() {
////						public void handleEvent(Event e) {
////							InputCompositeTracker inputTrackerObject = new InputCompositeTracker();
////							inputTrackerObject.setCompositeList(VariablePoJo
////									.getInstance().getCompositeList());
////							inputTrackerObject
////									.setInputComposite(inputHandlerObj
////											.getInputComposite());
////							inputTrackerObject
////									.setChildComposite_WorkSpace(VariablePoJo
////											.getInstance()
////											.getChildCreatorObject()
////											.getChildComposite_WorkSpace());
////							inputTrackerObject
////									.setParentComposite((CompositeWrapper) VariablePoJo
////											.getInstance()
////											.getChildCreatorObject()
////											.getChildComposite_WorkSpace()
////											.getParent());
//////							inputTrackerObject.setConnectorList(VariablePoJo
//////									.getInstance().getConnectorList());
////							inputTrackerObject.setConnectorList(ConnectorPoJo
////									.getInstance().getConnectorList());
////							inputTrackerObject.inputTracker();
////
////						}
////					});
//
//		} else if (obj.toString().equals("Output 1") || (obj.toString()
//				.equals("[Output 1]"))) {
//			System.out.println("in root -> parent2 -> leaf4 i am working");
//			
//			outputEvent();
//			
//		} else if (obj.toString().equals("Output 2") || (obj.toString()
//				.equals("[Output 2]"))) {
//			System.out.println("in root -> parent2 -> leaf5 i am working");
//			
//			outputEvent();
//
////			// get and object of the output handler object
////			final OutputHandler outputHandlerObj = new OutputHandler();
////
////			// Set all the required data
////			outputHandlerObj.setChildComposite_WorkSpace(VariablePoJo
////					.getInstance().getChildCreatorObject()
////					.getChildComposite_WorkSpace());
////			outputHandlerObj.setCompositeList(VariablePoJo.getInstance()
////					.getCompositeList());
//////			outputHandlerObj.setConnectorList(VariablePoJo.getInstance()
//////					.getConnectorList());
////			outputHandlerObj.setConnectorList(ConnectorPoJo.getInstance()
////					.getConnectorList());
////			outputHandlerObj.setParentComposite((CompositeWrapper) VariablePoJo
////					.getInstance().getChildCreatorObject()
////					.getChildComposite_WorkSpace().getParent());
////
////			// Call the method to create the composite on clicking the
////			outputHandlerObj.outputCompositeCreator();
////
////			// get the composite created by the output handler class and add
////			// listener to it to track movement
////			outputHandlerObj.getOutputComposite().addListener(SWT.MouseDown,
////					new Listener() {
////						public void handleEvent(Event e) {
////
////							OutputCompositeTracker outputTrackerObject = new OutputCompositeTracker();
////							outputTrackerObject.setCompositeList(VariablePoJo
////									.getInstance().getCompositeList());
////							outputTrackerObject
////									.setOutputComposite(outputHandlerObj
////											.getOutputComposite());
////							outputTrackerObject
////									.setChildComposite_WorkSpace(VariablePoJo
////											.getInstance()
////											.getChildCreatorObject()
////											.getChildComposite_WorkSpace());
////							outputTrackerObject
////									.setParentComposite((CompositeWrapper) VariablePoJo
////											.getInstance()
////											.getChildCreatorObject()
////											.getChildComposite_WorkSpace()
////											.getParent());
//////							outputTrackerObject.setConnectorList(VariablePoJo
//////									.getInstance().getConnectorList());
////							outputTrackerObject.setConnectorList(ConnectorPoJo
////									.getInstance().getConnectorList());
////							outputTrackerObject.outputTracker();
////						}
////					});
////
//		}
//
//	}
//	
//	public void inputEvent () {
//		// Set the required data
//		final InputHandler inputHandlerObj = new InputHandler();
//		inputHandlerObj.setChildComposite_WorkSpace(VariablePoJo
//				.getInstance().getChildCreatorObject()
//				.getChildComposite_WorkSpace());
//		inputHandlerObj.setCompositeList(VariablePoJo.getInstance()
//				.getCompositeList());
////		inputHandlerObj.setConnectorList(VariablePoJo.getInstance()
////				.getConnectorList());
//		inputHandlerObj.setConnectorList(VariablePoJo.getInstance()
//				.getConnectorList());
//
//		// Create the input composite
//		inputHandlerObj.inputCompositeCreator();
//
//		inputHandlerObj.getInputComposite().addListener(SWT.MouseDown,
//				new Listener() {
//					public void handleEvent(Event e) {
//						InputCompositeTracker inputTrackerObject = new InputCompositeTracker();
//						inputTrackerObject.setCompositeList(VariablePoJo
//								.getInstance().getCompositeList());
//						inputTrackerObject
//								.setInputComposite(inputHandlerObj
//										.getInputComposite());
//						inputTrackerObject
//								.setChildComposite_WorkSpace(VariablePoJo
//										.getInstance()
//										.getChildCreatorObject()
//										.getChildComposite_WorkSpace());
//						inputTrackerObject
//								.setParentComposite((CompositeWrapper) VariablePoJo
//										.getInstance()
//										.getChildCreatorObject()
//										.getChildComposite_WorkSpace()
//										.getParent());
////						inputTrackerObject.setConnectorList(VariablePoJo
////								.getInstance().getConnectorList());
//						inputTrackerObject.setConnectorList(VariablePoJo
//								.getInstance().getConnectorList());
//						inputTrackerObject.inputTracker();
//
//					}
//				});
//	}
//	
//	public void outputEvent () {
//		// get and object of the output handler object
//		final OutputHandler outputHandlerObj = new OutputHandler();
//
//		// Set all the required data
//		outputHandlerObj.setChildComposite_WorkSpace(VariablePoJo
//				.getInstance().getChildCreatorObject()
//				.getChildComposite_WorkSpace());
//		outputHandlerObj.setCompositeList(VariablePoJo.getInstance()
//				.getCompositeList());
////		outputHandlerObj.setConnectorList(VariablePoJo.getInstance()
////				.getConnectorList());
//		outputHandlerObj.setConnectorList(VariablePoJo.getInstance()
//				.getConnectorList());
//		outputHandlerObj.setParentComposite((CompositeWrapper) VariablePoJo
//				.getInstance().getChildCreatorObject()
//				.getChildComposite_WorkSpace().getParent());
//
//		// Call the method to create the composite on clicking the
//		outputHandlerObj.outputCompositeCreator();
//
//		// get the composite created by the output handler class and add
//		// listener to it to track movement
//		outputHandlerObj.getOutputComposite().addListener(SWT.MouseDown,
//				new Listener() {
//					public void handleEvent(Event e) {
//
//						OutputCompositeTracker outputTrackerObject = new OutputCompositeTracker();
//						outputTrackerObject.setCompositeList(VariablePoJo
//								.getInstance().getCompositeList());
//						outputTrackerObject
//								.setOutputComposite(outputHandlerObj
//										.getOutputComposite());
//						outputTrackerObject
//								.setChildComposite_WorkSpace(VariablePoJo
//										.getInstance()
//										.getChildCreatorObject()
//										.getChildComposite_WorkSpace());
//						outputTrackerObject
//								.setParentComposite((CompositeWrapper) VariablePoJo
//										.getInstance()
//										.getChildCreatorObject()
//										.getChildComposite_WorkSpace()
//										.getParent());
////						outputTrackerObject.setConnectorList(VariablePoJo
////								.getInstance().getConnectorList());
//						outputTrackerObject.setConnectorList(VariablePoJo
//								.getInstance().getConnectorList());
//						outputTrackerObject.outputTracker();
//					}
//				});
//	}
//
//}
