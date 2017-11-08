package hu.sztaki.ilab.ps.lasso.algorithm

import breeze.linalg._
import breeze.numerics.sqrt
import hu.sztaki.ilab.ps.lasso.LassoParameterServer.{LassoModel, LassoParam, OptionLabeledVector}

/**
  * TO COMPLETE WITH LASSO DETAILS
  *
  */
object LassoBasicAlgorithm {
  def buildLasso(): LassoBasicAlgorithm = new LassoBasicAlgorithmImpl()
}

/**
  *
  * @param aggressiveness set the aggressiveness level of the algorithm. Denoted by C in paper.
  */
abstract class LassoBasicAlgorithm(protected val aggressiveness: Double)
  extends LassoAlgorithm[OptionLabeledVector, LassoParam, Double, LassoModel] with Serializable {

  override def delta(dataPoint: OptionLabeledVector,
                     model: LassoModel,
                     label: Double): Iterable[(Int, LassoParam)] = {
    /* TO COMPLETE WITH LASSO DETAILS*/
    val x_t: DenseVector[Double] = dataPoint match {
      case Left((vec, _)) => vec
      case Right(vec) => vec
    }

    val a_t: DenseMatrix[Double] = x_t.asDenseMatrix * x_t.asDenseMatrix.t

    val A_t: DenseMatrix[Double] = model._1 + a_t + diag(DenseVector.fill(model._1.rows){sqrt(label)})

    val newLabel = model._2.asDenseMatrix.t * inv(A_t) * x_t

    val l_t: DenseVector[Double] = newLabel * x_t

    Array((0, (A_t, l_t))).toIterable
  }

  /**
    * Predict label based on the actual model
    *
    * @param dataPoint denoted by x_t in paper.
    * @param model     the corresponding model vector for the data. Denoted by w_t in paper.
    *                  The active keyset of the model vector should equal to the keyset of the data.
    * @return
    */
  override def predict(dataPoint: OptionLabeledVector, model: LassoModel): Double = {

    /* TO COMPLETE WITH LASSO DETAILS*/
    val x_t: DenseVector[Double] = dataPoint match {
      case Left((vec, _)) => vec
      case Right(vec) => vec
    }

    val A_t = model._1
    val b_t = model._2

    val y_t = b_t.toDenseMatrix * inv(A_t) * x_t

    y_t.data(0)
  }

}

class LassoBasicAlgorithmImpl extends LassoBasicAlgorithm(0){

}

