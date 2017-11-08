package hu.sztaki.ilab.ps.test.utils

import breeze.linalg.{DenseVector, SparseVector}
import breeze.numerics.abs
import hu.sztaki.ilab.ps.lasso.LassoParameterServer.LassoModel
import hu.sztaki.ilab.ps.lasso.algorithm.LassoBasicAlgorithm
import org.slf4j.LoggerFactory

class LassoBasicModelEvaluation

object LassoBasicModelEvaluation {

  private val log = LoggerFactory.getLogger(classOf[LassoBasicModelEvaluation])


  def accuracy(model: LassoModel,
               testLines: Traversable[(DenseVector[Double], Option[Double])],
               featureCount: Int,
               pac: LassoBasicAlgorithm): Double = {

    var cnt: Int = 0
    var sumDiffs: Double = 0.0
    testLines.foreach { case (vector, label) => label match {
      case Some(lab) =>
        val real = lab
        val predicted = pac.predict(Right(vector), model)
        sumDiffs += abs(predicted - real)
        cnt += 1
      case _ => throw new IllegalStateException("Labels shold not be missing.")
    }
    }
    val percent = sumDiffs / cnt

    percent
  }


}
