package hu.sztaki.ilab.ps.lasso.algorithm

import breeze.linalg._
import hu.sztaki.ilab.ps.lasso.LassoParameterServer.LassoParam

object LassoParameterInitializer {

  def initConcrete(a: Double, b: Double, n: Int): Int => LassoParam =
    _ => (diag(DenseVector.fill(n){a}), DenseVector.fill(n){b})
}
