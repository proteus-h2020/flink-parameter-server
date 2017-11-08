package hu.sztaki.ilab.ps.lasso.algorithm

/**
  * Common trait for Lasso algorithm.
  */
trait LassoAlgorithm[Vec, Param, Label, Model] extends Serializable {

  def delta(data: Vec,
            model: Model,
            label: Double): Iterable[(Int, Param)]

  def predict(dataPoint: Vec, model: Model): Label

}
